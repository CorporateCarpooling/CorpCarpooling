package com.example.mariadbservice.controller;

import com.example.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private List<Role> roles;
}
