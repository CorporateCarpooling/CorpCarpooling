package com.example.userservice.mappers;

import com.example.userservice.controller.RegisterUserRequest;
import com.example.model.User;
import com.example.userservice.controller.UpdateUserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterUserRequest registerUserRequest);
    User toUser(UpdateUserRequest updateUserRequest);
}
