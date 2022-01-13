package com.example.userservice.controller;

import com.example.userservice.domain.CarService;
import com.example.userservice.mappers.CarMapper;
import com.example.userservice.model.Car;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@AllArgsConstructor
@RestController
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @PostMapping ("/car/register")
    public ResponseEntity<String> registerCar(@RequestBody RegisterCarRequest registerCarRequest) {
 //       log.info("Car registration{}", registerCarRequest);
        Car car= carMapper.dtoToCar(registerCarRequest);
        carService.registerCar(car);
        return ResponseEntity.ok("Car registered");
    }
}