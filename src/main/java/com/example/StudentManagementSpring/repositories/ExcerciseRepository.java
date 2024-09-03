package com.example.StudentManagementSpring.repositories;

import com.example.StudentManagementSpring.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExcerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByTitle(String title);

    Optional<Exercise> findAllByTeacherSubjectIDId(Long id);

//    Optional<Exercise> enterExercise(String enterCode);

}
