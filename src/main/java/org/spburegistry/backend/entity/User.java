package org.spburegistry.backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.spburegistry.backend.enums.Role;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
@Data
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Nullable
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    @Nullable
    private Student student;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    @Nullable
    private Client client;
}

// spring boot why method find by id from jpa repository is return null but entity with this id exists in database