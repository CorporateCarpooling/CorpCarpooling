package com.example.bookingservice.service;

import com.example.bookingservice.clientapi.TripApi;
import com.example.model.Carpool;
import com.example.model.Passenger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class TripService {

    private TripApi tripApi;

    public void addPassengerToCarPool(long userId, Long carpoolId) {
        Carpool carpool = getCarpoolById(carpoolId);
        checkIfCarPoolHasAPassenger(carpool, userId);
        tripApi.joinATrip(carpoolId, userId);
    }

    public void removePassengerFromCarPool(long userId, Long carpoolId) {
        Carpool carpool = getCarpoolById(carpoolId);
        if (!passengerInCarpool(carpool, userId)) {
            throw new RuntimeException("Passenger not in carpool");
        }
        tripApi.leaveATrip(carpoolId, userId);
    }

    private Carpool getCarpoolById(Long carpoolId) {
        Optional<Carpool> carpoolIndataBase = tripApi.getCarPoolById(carpoolId);
        if (carpoolIndataBase.isEmpty()) {
            throw new RuntimeException("There is no carpool");
        }
        return carpoolIndataBase.get();
    }

    private void checkIfCarPoolHasAPassenger(Carpool carpool, long userId) {
        if (passengerInCarpool(carpool, userId)) {
            throw new RuntimeException("User already exist");
        }
    }

    private boolean passengerInCarpool(Carpool carpool, long userId) {
        return carpool.getPassengers().stream().anyMatch(passenger ->
                passenger.getUserId() == userId
        );
    }

    public void approvePassengerRequest(Long passengerId, Long driverId) {
        Optional<Passenger> passengerOptional = tripApi.getPassengersById(passengerId);
        if (passengerOptional.isEmpty()) {
            throw new RuntimeException("There is no passenger");
        }

        Carpool carpool = getCarpoolById(passengerOptional.get().getCarpoolId());
        if (carpool.getDriverId().longValue() != driverId.longValue()) {
            throw new RuntimeException("Not your Passenger");
        }

        boolean availableSeats = isAvailableSeats(carpool);
        if (!availableSeats) {
            throw new RuntimeException("There is no available seats");
        }

        tripApi.approvePassenger(passengerId);

    }

    private boolean isAvailableSeats(Carpool carpool) {
        int availableSeats = carpool.getAvailableSeatsForRide();
        long approvedPassengers = carpool.getPassengers().stream().filter(Passenger::getApproved).count();
        return availableSeats > approvedPassengers;
    }

}