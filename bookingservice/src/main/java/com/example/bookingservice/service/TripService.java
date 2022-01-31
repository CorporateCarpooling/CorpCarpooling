package com.example.bookingservice.service;

import com.example.bookingservice.clientapi.TripApi;
import com.example.bookingservice.mappers.JoinCarPoolMapper;
import com.example.model.Carpool;
import com.example.model.Passenger;
import com.example.request.CarPoolRequest;
import com.example.request.JoinCarpoolRequest;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
@Service
public class TripService {
    private TripApi tripApi;
    private JoinCarPoolMapper joinCarPoolMapper;


    public void addPassengerToCarPool(long userId, Long carpoolId){
        Carpool carpool = getCarpoolById(carpoolId);
        checkIfCarPoolHasAPassenger(carpool, userId);
        tripApi.joinATrip(carpoolId, userId);
    }

    private Carpool getCarpoolById(Long carpoolId) {
        Optional<Carpool> carpoolIndataBase = tripApi.getCarPoolById(carpoolId);
        if(carpoolIndataBase.isEmpty()) {
            throw new RuntimeException("There is no carpool");
        }
        return carpoolIndataBase.get();
    }

    private void checkIfCarPoolHasAPassenger(Carpool carpool,long userId) {
       for(Passenger passenger: carpool.getPassengers()) {
           if(passenger.getUser().getId() == userId) {
               throw new RuntimeException("User already exist");
           }
       }
    }

}