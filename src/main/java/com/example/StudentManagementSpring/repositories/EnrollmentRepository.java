package com.example.StudentManagementSpring.repositories;

import com.example.StudentManagementSpring.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
