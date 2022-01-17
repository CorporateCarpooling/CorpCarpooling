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

            carDataApi.updateCar(car).block();
            System.out.println(car.toString());
            System.out.println(carToUpdate.get());

        } else {
            throw new RuntimeException("Car doesn't exist. Please register a car");
        }
    }

    public void getCar(String regNumber) {

        Optional<Car> carInDatabase = carDataApi.getCarByRegistrationNumber(regNumber);

        if (carInDatabase.isPresent()) {

            System.out.println(carInDatabase.get());
        } else {
            throw new RuntimeException("Car doesn't exist. Please register a car");

        }
    }


}
