package com.example.StudentManagementSpring.mapper;

import com.example.StudentManagementSpring.entities.Exercise;
import com.example.StudentManagementSpring.response.ExerciseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExerciseMapper {
    @Mapping(source = "id", target = "exerciseID")
    ExerciseResponse toExerciseResponse(Exercise exercise);

    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    @Mapping(source = "exerciseID", target = "id")
    Exercise toExercise(ExerciseResponse exerciseResponse);
}
