package com.example.StudentManagementSpring.repositories;

import com.example.StudentManagementSpring.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
