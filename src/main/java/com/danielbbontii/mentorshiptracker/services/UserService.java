package com.danielbbontii.mentorshiptracker.services;

import com.danielbbontii.mentorshiptracker.dtos.AdminCreationDTO;
import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    ResponseDTO createAdmin(AdminCreationDTO adminCreationDTO, Authentication authentication);
}
