package com.example.StudentManagementSpring.services;

import com.example.StudentManagementSpring.entities.Exercise;
import com.example.StudentManagementSpring.entities.User;
import com.example.StudentManagementSpring.response.ExerciseResponse;

import java.util.List;

public interface ExerciseService {

    /**
     * Mo ta
     *
     * request
     * request
     * ....
     *
     * return
     * */
    List<ExerciseResponse> findAll(Long id);

    Exercise findById(Long id);

    ExerciseResponse findByIDByRes(Long id);

    ExerciseResponse findByTitle(String title);

    ExerciseResponse addOrModifyExercise(User loggedInUser, Exercise exercise);
}
