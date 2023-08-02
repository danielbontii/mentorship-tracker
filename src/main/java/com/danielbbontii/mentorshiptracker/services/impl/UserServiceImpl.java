package com.danielbbontii.mentorshiptracker.services.impl;

import com.danielbbontii.mentorshiptracker.dtos.AdminCreationDTO;
import com.danielbbontii.mentorshiptracker.dtos.CustomUserDetails;
import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.enums.UserType;
import com.danielbbontii.mentorshiptracker.models.User;
import com.danielbbontii.mentorshiptracker.repositories.RoleRepository;
import com.danielbbontii.mentorshiptracker.repositories.UserRepository;
import com.danielbbontii.mentorshiptracker.services.UserService;
import com.danielbbontii.mentorshiptracker.utils.PasswordUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResponseDTO createAdmin(AdminCreationDTO adminCreationDTO, Authentication authentication) {

        User newAdmin = objectMapper.convertValue(adminCreationDTO, User.class);

        String password = PasswordUtils.generatePassword();
        logger.info("GENERATED PASSWORD: {}", password);

        newAdmin.setPassword(passwordEncoder.encode(password));
        newAdmin.setUserType(UserType.ADMIN);
        newAdmin.setRole(roleRepository.findByNameEqualsIgnoreCase("Administrator"));

        //See if I can do this with AOP later

        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails authUser = (CustomUserDetails) authentication.getPrincipal();
            newAdmin.setCreatedBy(authUser.getUserId());
            newAdmin.setLastUpdatedBy(authUser.getUserId());
        }

        User savedAdmin = userRepository.save(newAdmin);
        return new ResponseDTO("success", objectMapper.convertValue(savedAdmin, AdminCreationDTO.class));
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsernameOrEmailEquals(usernameOrEmail);

        return userOptional
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }
}
