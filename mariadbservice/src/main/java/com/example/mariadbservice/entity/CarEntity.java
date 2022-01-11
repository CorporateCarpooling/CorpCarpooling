package com.example.mariadbservice.entity;

import com.example.mariadbservice.model.FuelType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    private CarBrandEntity carBrand;

    private String registrationNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    private YearModelEntity yearModel;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private int availableSeats;
    private double price;

}
