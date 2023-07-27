package com.danielbbontii.mentorshiptracker.services.impl;

import com.danielbbontii.mentorshiptracker.dtos.LoginRequestDTO;
import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.services.AuthService;
import com.danielbbontii.mentorshiptracker.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseDTO authenticateUsernameOrEmailAndPassword(LoginRequestDTO loginRequestDTO) {

        String usernameOrEmail = loginRequestDTO.getUsernameOrEmail();

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(usernameOrEmail,
                        loginRequestDTO.getPassword()));

        if (auth.isAuthenticated()) {
            return new ResponseDTO("success", Map.of("token", jwtUtils.generateToken(usernameOrEmail)));
        }

        throw new UsernameNotFoundException("Invalid credentials");
    }
}
