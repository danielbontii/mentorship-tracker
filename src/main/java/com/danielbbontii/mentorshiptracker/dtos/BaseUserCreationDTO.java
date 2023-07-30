package com.danielbbontii.mentorshiptracker.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserCreationDTO {

    @NotBlank(message = "first name must not be empty")
    private String firstname;

    @NotBlank(message = "last name must not be empty")
    private String lastname;

    @NotBlank(message = "email must not be empty")
    @Pattern(
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            message = "please provide a valid email")
    private String email;

    private String username;
}
