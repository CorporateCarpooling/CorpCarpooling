package com.example.mariadbservice.entity;


import com.example.model.FuelType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private CarBrandEntity carBrand;

    private String registrationNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    private YearModelEntity yearModel;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private int availableSeats;

}
