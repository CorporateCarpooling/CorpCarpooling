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
        Car car = carMapper.dtoToCar(carEntity);
        return car;
    }

    public String updateCar(String registrationNumber, Car car) {
        CarEntity newCarEntity = carMapper.carToCarDto(car);
        CarEntity carEntityFromDb = carRepository.findByRegistrationNumber(registrationNumber);
//        System.out.println(carEntityFromDb);

        carEntityFromDb.setAvailableSeats(newCarEntity.getAvailableSeats());
        carEntityFromDb.setPrice(newCarEntity.getPrice());
//        System.out.println(carRepository.save(carEntityFromDb));

        return carRepository.save(carEntityFromDb).toString();

    }

}

