package com.example.mariadbservice.domain;

import com.example.mariadbservice.entity.CarBrandEntity;
import com.example.mariadbservice.entity.CarEntity;
import com.example.mariadbservice.entity.YearModelEntity;
import com.example.mariadbservice.mappers.CarMapper;
import com.example.mariadbservice.model.Car;
import com.example.mariadbservice.repository.CarBrandRepository;
import com.example.mariadbservice.repository.CarRepository;
import com.example.mariadbservice.repository.YearModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class CarService {

    CarRepository carRepository;
    CarBrandRepository carBrandRepository;
    YearModelRepository yearModelRepository;
    private CarMapper carMapper;


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
        return carRepository.save(carEntity).getId();
    }

    public Car getCarByRegistrationNumber(String registrationNumber) {
        CarEntity carEntity = carRepository.findByRegistrationNumber(registrationNumber);
        return carMapper.dtoToCar(carEntity);
    }

    public String updateCar(Car car) {
        CarEntity carEntityFromDb = carRepository.findByRegistrationNumber(car.getRegistrationNumber());
        carEntityFromDb.setAvailableSeats(car.getAvailableSeats());
        return carRepository.save(carEntityFromDb).toString();

    }
    public void deleteCarByRegistrationNumber(String registrationNumber) {

        Optional<CarEntity> carInDatabase = Optional.ofNullable(carRepository.findByRegistrationNumber(registrationNumber));

        if (!carInDatabase.isPresent()) {
            throw new RuntimeException("Car doesn't exist. Please register a car");
        }
        carRepository.delete(carInDatabase.get());

    }
}

