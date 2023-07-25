package com.danielbbontii.mentorshiptracker.services.impl;

import com.danielbbontii.mentorshiptracker.dtos.RoleDTO;
import com.danielbbontii.mentorshiptracker.models.Permission;
import com.danielbbontii.mentorshiptracker.repositories.PermissionRepository;
import com.danielbbontii.mentorshiptracker.repositories.RoleRepository;
import com.danielbbontii.mentorshiptracker.services.RoleService;
import com.danielbbontii.mentorshiptracker.services.SeederService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleAndPermissionSeederServiceImpl implements SeederService {

    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    private static final String VIEW_MENTORSHIP_PERMISSION = "View mentorship";
    private static final String MANAGE_MENTORSHIP_PERMISSION = "Manage mentorship";
    private static final String MENTORSHIP_MANAGE_ROLE = "Mentorship manager";
    private static final String ADMIN_ROLE = "Administrator";

    @Override
    public void seed() {
        seedAdminRole();
        seedMentorshipManagerRole();
    }

    private void seedMentorshipManagerRole() {
        boolean isMentorshipManagerRoleSeeded = roleRepository.existsByNameIgnoreCase(MENTORSHIP_MANAGE_ROLE);

        if (!isMentorshipManagerRoleSeeded) {

            RoleDTO mentorshipManagerRoleDTO =
                    new RoleDTO(MENTORSHIP_MANAGE_ROLE, "Perform mentorship associated CRUD actions", new HashSet<>());

            addManageMentorShipPermission(mentorshipManagerRoleDTO);
            addViewMentorshipPermission(mentorshipManagerRoleDTO);

            roleService.create(mentorshipManagerRoleDTO, null);

        }
    }

    private void addViewMentorshipPermission(RoleDTO mentorshipManagerRoleDTO) {
        Optional<Permission> viewMentorshipOptional
                = permissionRepository.findByNameEqualsIgnoreCase(VIEW_MENTORSHIP_PERMISSION);

        if (viewMentorshipOptional.isPresent()) {
            mentorshipManagerRoleDTO.getPermissions().add(viewMentorshipOptional.get());
        } else {
            Permission viewMentorship = new Permission(
                    VIEW_MENTORSHIP_PERMISSION,
                    "view mentorship only",
                    null
            );
            mentorshipManagerRoleDTO.getPermissions().add(viewMentorship);
        }
    }

    private void addManageMentorShipPermission(RoleDTO mentorshipManagerRoleDTO) {
        Optional<Permission> manageMentorshipOptional =
                permissionRepository.findByNameEqualsIgnoreCase(MANAGE_MENTORSHIP_PERMISSION);

        if (manageMentorshipOptional.isPresent()) {
            mentorshipManagerRoleDTO.getPermissions().add(manageMentorshipOptional.get());
        } else {
            Permission manageMentorship = new Permission(
                    MANAGE_MENTORSHIP_PERMISSION,
                    "create, view, update and delete on mentorship(advisors and advisees)",
                    null
            );
            mentorshipManagerRoleDTO.getPermissions().add(manageMentorship);
        }

    }

    private void seedAdminRole() {
        boolean isAdminRoleSeeded = roleRepository.existsByNameIgnoreCase(ADMIN_ROLE);

        if (!isAdminRoleSeeded) {
            RoleDTO adminRoleDTO = new RoleDTO(ADMIN_ROLE, "Perform all actions", null);
            roleService.create(adminRoleDTO, null);
        }
    }
}
