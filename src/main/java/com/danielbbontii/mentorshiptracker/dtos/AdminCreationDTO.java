package com.danielbbontii.mentorshiptracker.dtos;

public class AdminCreationDTO extends BaseUserCreationDTO {

    @Override
    public String toString() {
        return "AdminCreationDTO(" +
                "firstname='" + super.getFirstname() + '\'' +
                ", lastname='" + super.getLastname() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", username='" + super.getUsername() + '\'' +
                ')';
    }

}
