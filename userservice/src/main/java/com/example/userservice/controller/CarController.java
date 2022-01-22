package com.example.userservice.controller;

import com.example.model.Car;
import com.example.userservice.service.CarService;
import com.example.userservice.mappers.CarMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @PostMapping("/car/register")
    public ResponseEntity<String> registerCar(@RequestBody RegisterCarRequest registerCarRequest) {
        Car car = carMapper.dtoToCar(registerCarRequest);
        carService.registerCar(car);
        return ResponseEntity.ok("Car registered");
    }

    @PatchMapping("/car/update")
    public ResponseEntity<String> updateCar(@RequestBody RegisterCarRequest registerCarRequest) {
        Car car = carMapper.dtoToCar(registerCarRequest);
        carService.updateCar(car);
        return ResponseEntity.ok("Car updated");
    }

    @GetMapping("/car/getbyregnumber")
    public ResponseEntity<Car> getCar(@RequestParam String regNumber) {
        Car car = carService.getCar(regNumber);
        return ResponseEntity.ok(car);
    }
}