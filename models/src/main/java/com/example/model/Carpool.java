package com.example.model;


import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class Carpool {

    private Long id;
    private Car car;
    private Route route;
    private List<Passenger> passengers;
    private LocalDateTime departureTime;
    private int availableSeatsForRide;
    private double pricePerRide;

}
