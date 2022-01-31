package com.example.bookingservice.controller;

import com.example.bookingservice.service.TripService;
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

}