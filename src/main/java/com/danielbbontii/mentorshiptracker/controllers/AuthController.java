package com.danielbbontii.mentorshiptracker.controllers;

import com.danielbbontii.mentorshiptracker.dtos.LoginRequestDTO;
import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/v1/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO authenticateUsernameOrEmailAndPassword(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return authService.authenticateUsernameOrEmailAndPassword(loginRequestDTO);
    }
}
