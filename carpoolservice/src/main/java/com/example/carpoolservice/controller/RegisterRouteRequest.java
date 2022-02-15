package com.example.carpoolservice.controller;

import com.example.model.Route;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterRouteRequest {

  private String registrationNumber;
  private Route route;
  private LocalDateTime departureTime;
  private int availableSeatsForRide;
  private double pricePerRide;
}
