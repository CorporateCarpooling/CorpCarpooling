package com.example.mariadbservice.mappers;

import com.example.mariadbservice.controller.UserRequest;
import com.example.mariadbservice.entity.UserEntity;
import com.example.mariadbservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //UserMapper Instance = Mappers.getMapper(UserMapper.class);
    UserEntity toEntity(User user);

    User toUser(UserRequest userRequest);

    User toUser(UserEntity userEntity);
    //User toUser(UserEntity savedEntity);
}
