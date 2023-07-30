package com.danielbbontii.mentorshiptracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Embedded
    Location location;
}
