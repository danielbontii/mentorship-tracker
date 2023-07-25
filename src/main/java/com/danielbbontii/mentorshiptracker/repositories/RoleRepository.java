package com.danielbbontii.mentorshiptracker.repositories;

import com.danielbbontii.mentorshiptracker.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    boolean existsByNameIgnoreCase(String name);


}
