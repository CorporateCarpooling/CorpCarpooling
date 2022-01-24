package com.example.userservice.service;

import com.example.model.Car;
import com.example.model.User;
import com.example.request.CarRequest;
import com.example.securityconfig.clientapi.SecurityApi;
import com.example.userservice.clientapi.CarDataApi;
import com.example.userservice.mappers.CarMapper;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CarService {

    private final CarDataApi carDataApi;
    private final SecurityApi securityApi;
    private final CarMapper carMapper;

    public void registerCar(Car car, Long userId) {
        Optional<User> userInDatabase = securityApi.getUserById(Long.toString(userId));
        if (!userInDatabase.isPresent()) {
            throw new RuntimeException("No user in database");
        }
        boolean noMatchingCar = userInDatabase.get().getCars() == null || userInDatabase.get().getCars().stream().
                noneMatch(carInUser -> carInUser.getRegistrationNumber().equals(car.getRegistrationNumber()));

        if (!noMatchingCar) {
            throw new RuntimeException("Car already Exist");
        } else {
            CarRequest carRequest = carMapper.carToCarRequest(car);
            carRequest.setUserId(userId);
            carDataApi.postCar(carRequest);
        }
    }


    public void updateCar(Car car, Long userId) {

        Optional<Car> carToUpdate = carDataApi.getCarByRegistrationNumber(car.getRegistrationNumber(), userId);

        if (carToUpdate.isPresent()) {
            CarRequest carRequest = carMapper.carToCarRequest(car);
            carRequest.setUserId(userId);
            carDataApi.updateCar(carRequest);
        } else {
            throw new RuntimeException("Car doesn't exist. Please register a car");
        }
    }

    public Car getCar(String regNumber, Long userId) {

        Optional<Car> carInDatabase = carDataApi.getCarByRegistrationNumber(regNumber, userId);

        if (carInDatabase.isPresent()) {
            return carInDatabase.get();
        } else {
            throw new RuntimeException("Car doesn't exist. Please register a car");
        }
    }


}
