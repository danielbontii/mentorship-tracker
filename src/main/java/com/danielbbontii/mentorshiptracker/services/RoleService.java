package com.danielbbontii.mentorshiptracker.services;

import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.dtos.RoleDTO;
import org.springframework.security.core.Authentication;

public interface RoleService {
    ResponseDTO create(RoleDTO roleDTO, Authentication authentication);
}
