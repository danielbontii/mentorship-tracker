package com.danielbbontii.mentorshiptracker.services.impl;

import com.danielbbontii.mentorshiptracker.dtos.ResponseDTO;
import com.danielbbontii.mentorshiptracker.dtos.RoleCreationDTO;
import com.danielbbontii.mentorshiptracker.exceptions.EntityAlreadyExistsException;
import com.danielbbontii.mentorshiptracker.models.Role;
import com.danielbbontii.mentorshiptracker.repositories.RoleRepository;
import com.danielbbontii.mentorshiptracker.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public ResponseDTO create(RoleCreationDTO roleCreationDTO, Authentication authentication) {

        boolean isRoleAlreadyExistent = roleRepository.existsByNameIgnoreCase(roleCreationDTO.getName());

        if (isRoleAlreadyExistent) {
            throw new EntityAlreadyExistsException("role with name %s already exists".formatted(roleCreationDTO.getName()));
        }

        Role roleToCreate = new Role();
        roleToCreate.setName(roleCreationDTO.getName().trim());

        if (roleCreationDTO.getDescription() != null) {
            roleToCreate.setDescription(roleCreationDTO.getDescription().trim());
        }
        if (roleCreationDTO.getPermissions() != null && !roleCreationDTO.getPermissions().isEmpty()) {
            roleToCreate.setPermissions(roleCreationDTO.getPermissions());
        }
        /*
         * Todo:
         *  If user is authenticated:
         *   Get the auth details from spring security
         *   Set the createdBy
         *   Set the lastUpdatedBy
         * */
        Role createdRole = roleRepository.save(roleToCreate);
        return new ResponseDTO("success", createdRole);
    }
}
