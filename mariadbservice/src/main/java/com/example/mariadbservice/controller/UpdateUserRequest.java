package com.example.mariadbservice.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateUserRequest {
    @NotNull
    private Long id;
    private String name;
    private String email;
    private String password;
}
