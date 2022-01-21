package com.example.mariadbservice.controller;

import com.example.model.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateUserRequest {
    @NotNull
    private Long id;
    private String name;
    private String email;
    private String password;
//    private List<Role> roles;
}
