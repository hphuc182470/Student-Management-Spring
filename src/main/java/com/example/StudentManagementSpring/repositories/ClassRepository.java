package com.example.StudentManagementSpring.repositories;

import com.example.StudentManagementSpring.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
}
