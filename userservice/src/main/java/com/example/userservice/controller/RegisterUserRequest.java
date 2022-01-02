package com.example.userservice.controller;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class RegisterUserRequest {

    private String name;
    @Email
    private String email;
    private String userName;
    private String password;
}
