package com.example.mariadbservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//@Table (name = "Year Model")
@Data
@NoArgsConstructor
public class YearModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String yearModel;


}