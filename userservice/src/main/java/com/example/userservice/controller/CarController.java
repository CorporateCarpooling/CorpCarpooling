package com.example.userservice.controller;

import com.example.model.Car;
import com.example.userservice.service.CarService;
import com.example.userservice.mappers.CarMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class CarController {

  private final CarService carService;
  private final CarMapper carMapper;

  @PostMapping("/car/register")
  public ResponseEntity<String> registerCar(Principal principal, @RequestBody RegisterCarRequest registerCarRequest) {
    Car car = carMapper.dtoToCar(registerCarRequest);
    carService.registerCar(car, Long.parseLong(principal.getName()));
    return ResponseEntity.ok("Car registered");
  }

  @PatchMapping("/car/update")
  public ResponseEntity<String> updateCar(Principal principal, @RequestBody RegisterCarRequest registerCarRequest) {
    Car car = carMapper.dtoToCar(registerCarRequest);
    carService.updateCar(car, Long.parseLong(principal.getName()));
    return ResponseEntity.ok("Car updated");
  }

  @GetMapping("/car/getbyregnumber")
  public ResponseEntity<Car> getCar(Principal principal, @RequestParam String regNumber) {
    Car car = carService.getCar(regNumber, Long.parseLong(principal.getName()));
    return ResponseEntity.ok(car);
  }

  @DeleteMapping("/car/delete")
  public ResponseEntity<String> deleteCar(Principal principal, @RequestParam String regNumber) {
    carService.deleteCar(regNumber, Long.parseLong(principal.getName()));
    return ResponseEntity.ok("Car deleted");
  }

  @GetMapping("/car/list")
  public ResponseEntity<List<Car>> getAllCars(Principal principal) {
    List<Car> cars = carService.getAllCars(Long.parseLong(principal.getName()));
    return ResponseEntity.ok(cars);
  }
}