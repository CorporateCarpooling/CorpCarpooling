package com.example.mariadbservice.controller;

import com.example.mariadbservice.domain.UserService;
import com.example.mariadbservice.mappers.UserMapper;
import com.example.mariadbservice.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("user")
    public ResponseEntity<String> postUser(@RequestBody UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        Long id = userService.create(user);
        return new ResponseEntity<>(Long.toString(id), HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<User> getUser(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}