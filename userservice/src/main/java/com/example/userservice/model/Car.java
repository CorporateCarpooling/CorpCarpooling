package com.example.userservice.model;

import lombok.Data;

@Data
public class Car {

    private Long id;
    private CarBrand carBrand;
    private String registrationNumber;
    private YearModel yearModel;
    private String fuelType;
    private int availableSeats;
    private double pricePerPassenger;

}