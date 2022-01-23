package com.example.userservice.domain;

import com.example.userservice.clientapi.CarDataApi;
import com.example.userservice.model.Car;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CarService {

    private final CarDataApi carDataApi;

    public void registerCar(Car car) {

        Optional<Car> carInDatabase = carDataApi.getCarByRegistrationNumber(car.getRegistrationNumber());

        if (carInDatabase.isPresent()) {
            throw new RuntimeException("Car already Exist");
        } else {
            carDataApi.postCar(car);
        }
    }


    public void updateCar(Car car) {

        Optional<Car> carToUpdate = carDataApi.getCarByRegistrationNumber(car.getRegistrationNumber());

        if (carToUpdate.isPresent()) {
            carDataApi.updateCar(car);
        } else {
            throw new RuntimeException("Car doesn't exist. Please register a car");
        }
    }

    public Car getCar(String regNumber) {

        Optional<Car> carInDatabase = carDataApi.getCarByRegistrationNumber(regNumber);

        if (carInDatabase.isPresent()) {
            return carInDatabase.get();
        } else {
            throw new RuntimeException("Car doesn't exist. Please register a car");
        }
    }
    public void deleteCar(String regNumber) {
        Optional<Car> carInDatabase = carDataApi.getCarByRegistrationNumber(regNumber);

        if (!carInDatabase.isPresent()) {
            throw new RuntimeException("Car doesn't exist. Please register a car");
        }
        carDataApi.deleteCar(regNumber);
    }


}
