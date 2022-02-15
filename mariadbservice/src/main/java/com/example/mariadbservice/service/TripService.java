package com.example.mariadbservice.service;

import com.example.mariadbservice.entity.CarpoolEntity;
import com.example.mariadbservice.entity.PassengerEntity;
import com.example.mariadbservice.entity.UserEntity;
import com.example.mariadbservice.repository.CarPoolRepository;
import com.example.mariadbservice.repository.PassengerRepository;
import com.example.mariadbservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class TripService {

    private final CarPoolRepository carPoolRepository;
    private final UserRepository userRepository;
    private final PassengerRepository passengerRepository;

    public Long join(Long carpoolId, Long userId) {
        Optional<CarpoolEntity> carpoolEntity = carPoolRepository.findById(carpoolId);
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        PassengerEntity passengerEntity = new PassengerEntity();
        if (carpoolEntity.isPresent()) {
            passengerEntity.setCarpool(carpoolEntity.get());
        } else {
            throw new RuntimeException("No carpool Found.");
        }

        if (userEntity.isPresent()) {
            passengerEntity.setUser(userEntity.get());
        } else {
            throw new RuntimeException("No user Found.");
        }

        passengerEntity.setApproved(false);

        return passengerRepository.save(passengerEntity).getId();
    }

    public void leave(Long carpoolId, Long userId) {
        List<PassengerEntity> allPassengers = passengerRepository.findAll();

        Optional<PassengerEntity> entity = allPassengers.stream().filter(passengerEntity -> Objects.equals(passengerEntity.getUser().getId(), userId) &&
                Objects.equals(passengerEntity.getCarpool().getId(), carpoolId)).findFirst();

        if (entity.isPresent()) {
            passengerRepository.delete(entity.get());
        } else {
            throw new RuntimeException("Passenger not found.");
        }
    }

    public Long approvePassenger(Long passengerId) {
        Optional<PassengerEntity> passengerEntity = passengerRepository.findById(passengerId);
        if (passengerEntity.isPresent()) {
            PassengerEntity passenger = passengerEntity.get();
            passenger.setApproved(true);
            return passengerRepository.save(passenger).getId();
        } else {
            throw new RuntimeException("No passenger Found.");
        }
    }
}
