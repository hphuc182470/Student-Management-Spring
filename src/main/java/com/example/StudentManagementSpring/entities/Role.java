package com.example.StudentManagementSpring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleID", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "roleName", length = 50)
    private String roleName;

    @OneToMany(mappedBy = "roleID")
    private Set<User> users = new LinkedHashSet<>();

}