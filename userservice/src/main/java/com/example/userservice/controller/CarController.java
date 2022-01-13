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
@NoArgsConstructor

@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;
    private CarMapper carMapper;

    @PostMapping ("/register")
    public ResponseEntity<String> registerCar(@RequestBody RegisterCarRequest registerCarRequest) {
 //       log.info("Car registration{}", registerCarRequest);
        Car car= carMapper.dtoToCar(registerCarRequest);
        carService.registerCar(car);
        return ResponseEntity.ok("Car registered");
    }
}