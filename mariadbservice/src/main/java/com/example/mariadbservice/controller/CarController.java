package com.example.mariadbservice.controller;

import com.example.mariadbservice.service.CarService;
import com.example.mariadbservice.mappers.CarMapper;

import com.example.model.Car;
import com.example.request.CarRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class CarController {

    private CarService carService;
    private CarMapper carMapper;

    @PostMapping("car/register")
    public ResponseEntity<String> addCar(@RequestBody CarRequest carRequest) {
        Car car = carMapper.dtoToCar(carRequest);
        carService.createCar(car, carRequest.getUserId());
        return ResponseEntity.ok("Car registred");
    }

    @GetMapping("car")
    public ResponseEntity<Car> getCar(@RequestParam String registrationNumber, @RequestParam Long userId) {
        Car car = carService.getCarByRegistrationNumber(registrationNumber, userId);
        return ResponseEntity.ok(car);
    }
    @PatchMapping("car/update")
        public ResponseEntity<Car> updateCar(@RequestBody CarRequest carRequest) {
        Car car = carMapper.dtoToCar(carRequest);
        carService.updateCar(car, carRequest.getUserId());
        return ResponseEntity.ok(car);
    }
}
