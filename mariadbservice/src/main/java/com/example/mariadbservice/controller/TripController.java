package com.example.mariadbservice.controller;

import com.example.mariadbservice.service.TripService;
import com.example.request.JoinCarpoolRequest;
import com.example.request.PassengerApproveRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TripController {
    private TripService tripService;

    @PostMapping("join/trip")
    public ResponseEntity<String>JoinATrip(@RequestBody JoinCarpoolRequest joinCarpoolRequest){
        Long id = tripService.join(joinCarpoolRequest.getCarpoolId(), joinCarpoolRequest.getUserId());
        return ResponseEntity.ok(Long.toString(id));
    }

    @PostMapping("passenger/approve")
    public ResponseEntity<String> JoinATrip(@RequestBody PassengerApproveRequest passengerApproveRequest){
        Long id = tripService.approvePassenger(passengerApproveRequest.getPassengerId());
        return ResponseEntity.ok(Long.toString(id));
    }


}
