package com.danielbbontii.mentorshiptracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @Column(updatable = false)
    private UUID createdBy;

    @JsonIgnore
    @CreationTimestamp(source = SourceType.DB)
    @Column(updatable = false, nullable = false)
    private Instant createdOn;

    @JsonIgnore
    @UpdateTimestamp(source = SourceType.DB)
    @Column(insertable = false, nullable = false)
    private Instant lastUpdatedOn;

    @JsonIgnore
    @Column(insertable = false)
    private UUID lastUpdatedBy;

    @JsonIgnore
    @Column(insertable = false)
    private Instant deletedOn;

    @JsonIgnore
    @Column(insertable = false)
    private UUID deletedBy;
}
