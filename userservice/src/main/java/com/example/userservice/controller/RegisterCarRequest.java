package com.example.userservice.controller;

import com.example.model.CarBrand;
import com.example.model.YearModel;
import lombok.Data;

@Data
public class RegisterCarRequest {
    private CarBrand carBrand;
    private String registrationNumber;
    private YearModel yearModel;
    private String fuelType;
    private int availableSeats;

}
