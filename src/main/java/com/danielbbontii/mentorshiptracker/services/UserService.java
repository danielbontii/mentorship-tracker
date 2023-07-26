package com.danielbbontii.mentorshiptracker.services;

import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.dtos.UserCreationDTO;
import org.springframework.security.core.Authentication;

public interface UserService {

    ResponseDTO create(UserCreationDTO userCreationDTO, Authentication authentication);
}
