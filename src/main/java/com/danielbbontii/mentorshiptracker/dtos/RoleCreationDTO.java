package com.danielbbontii.mentorshiptracker.dtos;


import com.danielbbontii.mentorshiptracker.models.Permission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreationDTO {

    @NotBlank(message = "role name must not be empty")
    private String name;

    private String description;

    private Set<@NotNull Permission> permissions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleCreationDTO roleCreationDTO = (RoleCreationDTO) o;
        return Objects.equals(name, roleCreationDTO.name) && Objects.equals(description, roleCreationDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
