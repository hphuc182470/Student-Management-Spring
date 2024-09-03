package com.example.StudentManagementSpring.repositories;

import com.example.StudentManagementSpring.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
