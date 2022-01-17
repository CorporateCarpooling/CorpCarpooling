package com.example.userservice.domain;

import com.example.userservice.clientapi.CarDataApi;
import com.example.userservice.model.Car;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;


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

}
