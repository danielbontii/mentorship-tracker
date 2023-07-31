package com.danielbbontii.mentorshiptracker.config;

import com.danielbbontii.mentorshiptracker.filters.JwtAuthFilter;
import com.danielbbontii.mentorshiptracker.repositories.RoleRepository;
import com.danielbbontii.mentorshiptracker.repositories.UserRepository;
import com.danielbbontii.mentorshiptracker.services.UserService;
import com.danielbbontii.mentorshiptracker.services.impl.UserServiceImpl;
import com.danielbbontii.mentorshiptracker.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final ObjectMapper objectMapper;
    private final RoleRepository roleRepository;
    AuthenticationEntryPoint authEntryPoint;

    public SecurityConfig(UserRepository userRepository,
                          JwtUtils jwtUtils,
                          ObjectMapper objectMapper,
                          RoleRepository roleRepository,
                          @Qualifier("delegatedAuthenticationEntryPoint") AuthenticationEntryPoint authEntryPoint) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.objectMapper = objectMapper;
        this.roleRepository = roleRepository;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository, objectMapper, passwordEncoder(), roleRepository);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService());
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/v1/auth/login", "/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll()
                        .requestMatchers("/api/v1/users/admin/**").hasRole("ADMINISTRATOR")
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(new JwtAuthFilter(jwtUtils, userService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
