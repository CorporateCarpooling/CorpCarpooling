package com.example.mariadbservice.controller;

import com.example.mariadbservice.domain.CarService;
import com.example.mariadbservice.entity.CarBrandEntity;
import com.example.mariadbservice.entity.YearModelEntity;
import com.example.mariadbservice.mappers.CarMapper;
import com.example.mariadbservice.model.Car;
import com.example.mariadbservice.model.CarBrand;
import com.example.mariadbservice.repository.CarBrandRepository;
import com.example.mariadbservice.repository.CarRepository;
import com.example.mariadbservice.repository.YearModelRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/car")
public class CarController {

    CarRepository carRepository;
    CarBrandRepository carBrandRepository;
    YearModelRepository yearModelRepository;
    private CarMapper carMapper;


    @PostMapping("/add")
    public Long createCar(Car car) {
        var carEntity = carMapper.carToCarDto(car);
        CarBrandEntity carBrandEntity = carBrandRepository.findByBrandName(carEntity.getCarBrand().getBrandName());
        if (carBrandEntity == null) {
            carBrandEntity = carBrandRepository.save(carEntity.getCarBrand());
        }
        YearModelEntity yearModelEntity = yearModelRepository.findByYearModel(carEntity.getYearModel().getYearModel());
        if (yearModelEntity == null) {
            yearModelEntity = yearModelRepository.save(carEntity.getYearModel());
        }

        carEntity.setCarBrand(carBrandEntity);
        carEntity.setYearModel(yearModelEntity);
        System.out.println("in service class");
        return carRepository.save(carEntity).getId();
    }

    /*
    @PostMapping
    public void registerCar(@RequestBody CarRegistrationRequest carRegistrationRequest) {
        log.info("new car registration {}", carRegistrationRequest);
        carService.registerCar(carRegistrationRequest);
    }

     */

    /*
        @GetMapping("/all")
        public ResponseEntity<List<CarEntity>> findAll() {
            return ResponseEntity.ok(carMapper.toCarDTOs(carService.findAll()));
        }
    */
}