package com.danielbbontii.mentorshiptracker.repositories;

import com.danielbbontii.mentorshiptracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);
}
