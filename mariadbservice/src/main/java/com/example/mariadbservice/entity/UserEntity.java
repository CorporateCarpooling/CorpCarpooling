package com.example.mariadbservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
     @LazyCollection(LazyCollectionOption.FALSE)
     @OneToMany(cascade = CascadeType.ALL)
     private List<CarEntity> cars;
     @LazyCollection(LazyCollectionOption.FALSE)
     @ManyToMany
     @Enumerated(EnumType.STRING)
     private List<RoleEntity> roles;
}