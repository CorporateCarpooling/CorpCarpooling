package com.example.userservice.mappers;

import com.example.userservice.controller.RegisterUserRequest;
import com.example.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterUserRequest registerUserRequest);
}
