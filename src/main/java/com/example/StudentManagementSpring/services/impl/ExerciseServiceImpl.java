package com.example.StudentManagementSpring.services.impl;

import com.example.StudentManagementSpring.dto.RegisterDTO;
import com.example.StudentManagementSpring.entities.Exercise;
import com.example.StudentManagementSpring.entities.Subject;
import com.example.StudentManagementSpring.entities.TeacherSubject;
import com.example.StudentManagementSpring.entities.User;
import com.example.StudentManagementSpring.globalExceptions.NotFoundException;
import com.example.StudentManagementSpring.mapper.ExerciseMapper;
import com.example.StudentManagementSpring.repositories.ExcerciseRepository;
import com.example.StudentManagementSpring.repositories.SubjectRepository;
import com.example.StudentManagementSpring.repositories.TeacherSubjectRepository;
import com.example.StudentManagementSpring.response.ExerciseResponse;
import com.example.StudentManagementSpring.services.ExerciseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExcerciseRepository excerciseRepository;

    private final SubjectRepository subjectRepository;

    private final TeacherSubjectRepository teacherSubjectRepository;

    // (teacher / student) both can go into this
    // need to fix ------- only allow to get All in their major !!
    /**
     * Func to find all Exercise basing on their major, then return to ExerciseResponse using mapstruct
     *
     * @param id {@link Long}
     * @return ExerciseResponse
     */
    @Override
    public List<ExerciseResponse> findAll(Long id) {

        Optional<Exercise> exercises = excerciseRepository.findAllByTeacherSubjectIDId(id);
        if (exercises.isEmpty()) {
            throw new NotFoundException("No exercises found");
        }
        return exercises.stream()
                .map(ExerciseMapper.INSTANCE::toExerciseResponse)
                .collect(Collectors.toList());
    }

    // (teacher / student) both can go into this
    /**
     * Func to find Exercise by id
     *
     * @param id {@link Long}
     * @return ExerciseResponse
     */
    @Override
    public Exercise findById(Long id) {
        return excerciseRepository.findById(id).orElse(null);
    }

    // (teacher / student) both can go into this by Response
    /**
     * Func to find Exercise by id, then return ExerciseResponse using mapstruct
     *
     * @param id {@link Long}
     * @return ExerciseResponse
     */
    @Override
    public ExerciseResponse findByIDByRes(Long id) {
        Optional<Exercise> exercise = excerciseRepository.findById(id);
        return exercise.map(ExerciseMapper.INSTANCE::toExerciseResponse)
                .orElseThrow(() -> new NotFoundException("Exercise not found by id: " + id));
    }

    // both teacher and student
    /**
     * Func to find Exercise by title, then return ExerciseResponse using mapstruct
     *
     * @param title {@link String}
     * @return ExerciseResponse
     */
    @Override
    public ExerciseResponse findByTitle(String title) {
        Optional<Exercise> exercise = excerciseRepository.findByTitle(title);
        return exercise.map(ExerciseMapper.INSTANCE::toExerciseResponse)
                .orElseThrow(() -> new NotFoundException("Exercise not found by title: " + title));
    }

    // only student can enter to do exercise
//    public Exercise enterExercise(String enterCode) {
//        return excerciseRepository.enterExercise(enterCode).orElse(null);
//    }

    // create / modify ex (teacher)
    // need to fix ------- only allow to get All in their major !!
    /**
     * Func to add or modify an exercise, then return ExerciseResponse using mapstruct
     *
     * @param loggedInUser {@link User}
     * @param exercise {@link Exercise}
     * @return ExerciseResponse
     */
    @Override
    public ExerciseResponse addOrModifyExercise(User loggedInUser, Exercise exercise) {

        // check Major of loggedUser
        User teacher = loggedInUser;
        Subject subject = subjectRepository.findById(exercise.getSubjectID().getId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        TeacherSubject teacherSubject = teacherSubjectRepository
                .findByTeacherIDAndSubjectID(teacher, subject)
                .orElseThrow(() -> new RuntimeException("You are not authorized to create exercises for this subject"));

        Exercise existingExercise = excerciseRepository.findByTitle(exercise.getTitle()).orElse(null);
        if (existingExercise != null) {
            existingExercise.setTitle(exercise.getTitle());
            existingExercise.setSubjectID(subjectRepository.findById(exercise.getSubjectID().getId())
                    .orElseThrow(() -> new RuntimeException("Subject not found")));
            existingExercise.setEnterCode(exercise.getEnterCode());

            // Initialize any lazy-loaded properties
            Hibernate.initialize(existingExercise.getSubjectID());
            Exercise savedExercise = excerciseRepository.save(existingExercise);
            return ExerciseMapper.INSTANCE.toExerciseResponse(savedExercise);
        }

        Exercise newExercise = new Exercise();

        newExercise.setId(exercise.getId());
        newExercise.setTitle(exercise.getTitle());
        newExercise.setSubjectID(subjectRepository.findById(exercise.getSubjectID().getId())
                .orElseThrow(() -> new RuntimeException("Subject not found")));
        newExercise.setEnterCode(exercise.getEnterCode());

        // Initialize any lazy-loaded properties
        Hibernate.initialize(newExercise.getSubjectID());
        Exercise savedExercise = excerciseRepository.save(newExercise);
        return ExerciseMapper.INSTANCE.toExerciseResponse(savedExercise);
    }
}
