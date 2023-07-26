package com.danielbbontii.mentorshiptracker.dtos;

import com.danielbbontii.mentorshiptracker.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {

    @NotBlank(message = "first name must not be empty")
    private String firstName;

    @NotBlank(message = "last name must not be empty")
    private String lastName;

    @NotBlank(message = "email must not be empty")
    @Pattern(
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$",
            message = "please provide a valid email")
    private String email;

    @NotBlank(message = "username must not be empty")
    private String username;

    private LocalDate dateOfBirth;
    private String city;
    private String country;

    @NotNull(message = "user type must not be null")
    private UserType userType;

    @NotNull(message = "role id must not be null")
    private UUID roleId;
}
