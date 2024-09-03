package com.example.StudentManagementSpring.repositories;

import com.example.StudentManagementSpring.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
