package com.danielbbontii.mentorshiptracker.services;

import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.dtos.UserCreationDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    ResponseDTO create(UserCreationDTO userCreationDTO, Authentication authentication);
}
