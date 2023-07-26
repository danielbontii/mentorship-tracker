package com.danielbbontii.mentorshiptracker.services.impl;

import com.danielbbontii.mentorshiptracker.enums.UserType;
import com.danielbbontii.mentorshiptracker.models.Role;
import com.danielbbontii.mentorshiptracker.models.User;
import com.danielbbontii.mentorshiptracker.repositories.RoleRepository;
import com.danielbbontii.mentorshiptracker.repositories.UserRepository;
import com.danielbbontii.mentorshiptracker.services.SeederService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Qualifier("admin")
public class AdminSeederServiceImpl implements SeederService {

    private final SeederService roleAndPermissionSeederService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    public AdminSeederServiceImpl(@Qualifier("role and permissions") final SeederService roleAndPermissionSeederService,
                                  final RoleRepository roleRepository,
                                  final UserRepository userRepository,
                                  final BCryptPasswordEncoder passwordEncoder) {
        this.roleAndPermissionSeederService = roleAndPermissionSeederService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seed() {

        boolean isAdminAlreadySeeded = userRepository.existsByEmail(adminEmail);

        if (!isAdminAlreadySeeded) {

            roleAndPermissionSeederService.seed();

            Role adminRole = roleRepository.findByNameEqualsIgnoreCase("Administrator");

            User admin = new User();
            admin.setFirstname("Super");
            admin.setLastname("Admin");
            admin.setUsername(adminUsername);
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setUserType(UserType.ADMIN);
            admin.setRole(adminRole);

            userRepository.save(admin);

        }

    }
}
