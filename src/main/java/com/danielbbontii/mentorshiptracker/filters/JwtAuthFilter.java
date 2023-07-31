package com.danielbbontii.mentorshiptracker.filters;

import com.danielbbontii.mentorshiptracker.dtos.CustomUserDetails;
import com.danielbbontii.mentorshiptracker.services.UserService;
import com.danielbbontii.mentorshiptracker.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        try {
            final String AUTH_HEADER = request.getHeader("Authorization");

            String token = null;
            String username = null;

            if (AUTH_HEADER != null && AUTH_HEADER.startsWith("Bearer ")) {
                token = AUTH_HEADER.substring(7);
                username = jwtUtils.extractUsername(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                CustomUserDetails userDetails = (CustomUserDetails) userService.loadUserByUsername(username);

                if (Boolean.TRUE.equals(jwtUtils.validateToken(token, userDetails))) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e);
            filterChain.doFilter(request, response);
        }
    }
}
