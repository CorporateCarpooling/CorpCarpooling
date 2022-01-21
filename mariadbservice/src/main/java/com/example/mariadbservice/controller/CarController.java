package com.example.mariadbservice.controller;

import com.example.mariadbservice.domain.CarService;
import com.example.mariadbservice.mappers.CarMapper;
import com.example.mariadbservice.model.Car;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
        Long id = carService.createCar(car);
        return ResponseEntity.ok("Car registred");
    }

    @GetMapping("car")
    public ResponseEntity<Car> getCar(@RequestParam String registrationNumber) {
       Car car = carService.getCarByRegistrationNumber(registrationNumber);
        return ResponseEntity.ok(car);
    }
    @PatchMapping("car/update")
        public ResponseEntity<Car> updateCar(@RequestBody CarRequest carRequest) {
        Car car = carMapper.dtoToCar(carRequest);
        carService.updateCar(car);
        return ResponseEntity.ok(car);
    }
}