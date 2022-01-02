package com.example.userservice.controller;

import com.example.userservice.domain.UserService;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private final UserMapper userMapper;
    @PostMapping ("/user/register")
    public void registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        log.info("user registration{}", registerUserRequest);
//        User user= User.builder()
//                .name(registerUserRequest.getFirstName())
//                .lastName(registerUserRequest.getLastName())
//                .email(registerUserRequest.getEmail())
//                .build();
        User user= userMapper.toUser(registerUserRequest);
        userService.registerCustomer(user);
    }

}