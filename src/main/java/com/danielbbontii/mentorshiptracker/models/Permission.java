package com.danielbbontii.mentorshiptracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permissions")
public class Permission extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
