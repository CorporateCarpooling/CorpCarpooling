package com.example.carpoolservice.controller;

import com.example.model.Route;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterRouteRequest {

    //private Long id;
    private String registrationNumber;
    private Route route;
    //    private List<Passenger> passengers;
    private LocalDateTime departureTime;
    private int availableSeatsForRide;
    private double pricePerRide;
}
