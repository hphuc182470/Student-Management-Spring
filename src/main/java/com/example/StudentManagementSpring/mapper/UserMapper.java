package com.example.StudentManagementSpring.mapper;

import com.example.StudentManagementSpring.entities.User;
import com.example.StudentManagementSpring.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    @Mapping(source = "id", target = "userID")
    UserResponse userToUserResponse(User userInfo);

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userID", target = "id")
    User toEntity(UserResponse dto);
}
