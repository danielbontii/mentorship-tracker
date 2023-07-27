package com.danielbbontii.mentorshiptracker.services.impl;

import com.danielbbontii.mentorshiptracker.dtos.CustomUserDetails;
import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.dtos.UserCreationDTO;
import com.danielbbontii.mentorshiptracker.models.User;
import com.danielbbontii.mentorshiptracker.repositories.UserRepository;
import com.danielbbontii.mentorshiptracker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseDTO create(UserCreationDTO userCreationDTO, Authentication authentication) {

        //validate role
        //create new user
        //validate dob if user type is not admin
        //set profile user type is not admin
        //if authenticated set created by and last updated by
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsernameOrEmailEquals(usernameOrEmail);

        return userOptional
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }
}
