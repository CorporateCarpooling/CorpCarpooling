package com.example.mariadbservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {
     @Id
     @GeneratedValue
     private Long id;
     private String name;
     private String email;
     private String password;
     @ManyToMany(fetch = FetchType.EAGER)
     private List<RoleEntity> roles;
}