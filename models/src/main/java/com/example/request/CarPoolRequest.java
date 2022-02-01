package com.example.request;

import com.example.model.Route;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class CarPoolRequest {

    //private Long id;
    private Long carId;
    private Route route;
//    private List<Passenger> passengers;
    private LocalDateTime departureTime;
    private int availableSeatsForRide;
    private double pricePerRide;

    private Long driverUserId;

}
