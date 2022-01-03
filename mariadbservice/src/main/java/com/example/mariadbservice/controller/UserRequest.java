package com.example.mariadbservice.controller;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String userName;
    private String password;
}
