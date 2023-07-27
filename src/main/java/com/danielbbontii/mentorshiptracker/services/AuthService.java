package com.danielbbontii.mentorshiptracker.services;

import com.danielbbontii.mentorshiptracker.dtos.LoginRequestDTO;
import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;

public interface AuthService {
    ResponseDTO authenticateUsernameOrEmailAndPassword(LoginRequestDTO loginRequestDTO);
}
