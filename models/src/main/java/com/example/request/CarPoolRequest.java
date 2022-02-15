package com.example.request;

import com.example.model.Route;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarPoolRequest {

  private Long carId;
  private Route route;
  private LocalDateTime departureTime;
  private int availableSeatsForRide;
  private double pricePerRide;
  private Long driverUserId;
}
