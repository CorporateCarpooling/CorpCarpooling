package com.example.userservice.controller;

import com.example.userservice.domain.UserService;
import com.example.userservice.mappers.UserMapper;
import com.example.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        log.info("user registration{}", registerUserRequest);
        User user = userMapper.toUser(registerUserRequest);
        userService.registerCustomer(user);
        return ResponseEntity.ok("User Registered.");
    }


}