package com.danielbbontii.mentorshiptracker.services.impl;

import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.dtos.UserCreationDTO;
import com.danielbbontii.mentorshiptracker.services.UserService;
import org.springframework.security.core.Authentication;

public class UserServiceImpl implements UserService {
    @Override
    public ResponseDTO create(UserCreationDTO userCreationDTO, Authentication authentication) {

        //create new user
        //validate dob if user type is not admin
        //set profile user type is not admin
        //if authenticated set created by and last updated by
        return null;
    }
}
