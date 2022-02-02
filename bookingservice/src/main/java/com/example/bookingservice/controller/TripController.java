package com.example.bookingservice.controller;

import com.example.bookingservice.service.TripService;
import com.example.request.PassengerApproveRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@AllArgsConstructor
@RestController
public class TripController {
    private final TripService tripService;

    @PostMapping("join/carpool")
    public ResponseEntity<String> joinCarpool(Principal principal, @RequestBody JoinCarpoolRequest joinCarpoolRequest){
        tripService.addPassengerToCarPool(Long.parseLong(principal.getName()),joinCarpoolRequest.getCarpoolId());
        return ResponseEntity.ok("request send");
    }
    @PostMapping("approve/passenger")
    public ResponseEntity<String> approveCarpool(Principal principal, @RequestBody PassengerApproveRequest passengerApproveRequest){
        tripService.approvePassengerRequest(passengerApproveRequest.getPassengerId(), Long.parseLong(principal.getName()));
        return ResponseEntity.ok("Passenger request approved");
    }

}