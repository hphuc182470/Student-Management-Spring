package com.example.StudentManagementSpring.repositories;

import com.example.StudentManagementSpring.entities.Subject;
import com.example.StudentManagementSpring.entities.TeacherSubject;
import com.example.StudentManagementSpring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {
    Optional<TeacherSubject> findByTeacherIDAndSubjectID(User teacherID, Subject subjectID);

}
