package com.danielbbontii.mentorshiptracker.services;

import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.dtos.RoleCreationDTO;
import org.springframework.security.core.Authentication;

public interface RoleService {
    ResponseDTO create(RoleCreationDTO roleCreationDTO, Authentication authentication);
}
