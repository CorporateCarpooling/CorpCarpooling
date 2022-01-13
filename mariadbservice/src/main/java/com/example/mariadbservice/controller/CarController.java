package com.example.mariadbservice.controller;

import com.example.mariadbservice.domain.CarService;
import com.example.mariadbservice.mappers.CarMapper;
import com.example.mariadbservice.model.Car;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/car")
public class CarController {

    private CarService carService;
    private CarMapper carMapper;

    @PostMapping("/add")
    public ResponseEntity<String> addCar(@RequestBody CarRequest carRequest) {
        Car car = carMapper.dtoToCar(carRequest);
        Long id = carService.createCar(car);
        return ResponseEntity.ok("Car registered");
    }


}