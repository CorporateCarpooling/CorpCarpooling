package com.example.userservice.model;

import lombok.Data;

@Data
public class Car {

    private Long id;
    private String carBrand;
    private String registrationNumber;
    private String yearModelId;
    private String fuelTypeId;
    private int availableSeats;
    private double pricePerPassenger;

}