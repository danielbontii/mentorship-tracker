package com.danielbbontii.mentorshiptracker.repositories;

import com.danielbbontii.mentorshiptracker.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {

    Optional<Permission> findByNameEqualsIgnoreCase(String name);
}
