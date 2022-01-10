package com.example.mariadbservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
//@Table(name = "Car Brand")
@Data
@NoArgsConstructor
public class CarBrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String brandName;


}
