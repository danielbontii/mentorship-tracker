package com.danielbbontii.mentorshiptracker.controllers;

import com.danielbbontii.mentorshiptracker.dtos.AdminCreationDTO;
import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("v1/users/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createAdmin(@Valid @RequestBody AdminCreationDTO adminCreationDTO, Authentication authentication) {
        return userService.createAdmin(adminCreationDTO, authentication);
    }
}
