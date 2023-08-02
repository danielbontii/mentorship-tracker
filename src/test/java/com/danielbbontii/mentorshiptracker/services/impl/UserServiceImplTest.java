package com.danielbbontii.mentorshiptracker.services.impl;

import com.danielbbontii.mentorshiptracker.dtos.AdminCreationDTO;
import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.models.Role;
import com.danielbbontii.mentorshiptracker.models.User;
import com.danielbbontii.mentorshiptracker.repositories.RoleRepository;
import com.danielbbontii.mentorshiptracker.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    ObjectMapper objectMapper;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeAll
    static void setUp() {

    }

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenNonExistentDTO_whenCreateAdmin_thenReturnAdmin() {

        AdminCreationDTO adminDto = new AdminCreationDTO();
        adminDto.setEmail("kang@gmail.com");
        adminDto.setFirstname("Liu");
        adminDto.setLastname("Kang");
        adminDto.setUsername("liukang");
        System.out.println(adminDto);

        User admin = User.builder()
                .firstname(adminDto.getFirstname())
                .lastname(adminDto.getLastname())
                .email(adminDto.getEmail())
                .username(adminDto.getFirstname())
                .build();

        when(objectMapper.convertValue(adminDto, User.class)).thenReturn(admin);
        when(userRepository.save(any(User.class))).thenReturn(admin);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(roleRepository.findByNameEqualsIgnoreCase("Administrator")).thenReturn(new Role());

        when(objectMapper.convertValue(admin, AdminCreationDTO.class)).thenReturn(adminDto);

        ResponseDTO actualResponse = new ResponseDTO("success", adminDto);
        ResponseDTO expectedResponse = userService.createAdmin(adminDto, null);

        assertEquals(expectedResponse, actualResponse);

    }

}