package com.danielbbontii.mentorshiptracker.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "username or email must not be empty")
    private String usernameOrEmail;

    @NotBlank(message = "password must not be empty")
    private String password;
}
