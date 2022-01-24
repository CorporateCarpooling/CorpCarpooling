package com.example.mariadbservice.service;

import com.example.mariadbservice.entity.CarBrandEntity;
import com.example.mariadbservice.entity.CarEntity;
import com.example.mariadbservice.entity.UserEntity;
import com.example.mariadbservice.entity.YearModelEntity;
import com.example.mariadbservice.mappers.CarMapper;

import com.example.mariadbservice.repository.CarBrandRepository;
import com.example.mariadbservice.repository.CarRepository;
import com.example.mariadbservice.repository.UserRepository;
import com.example.mariadbservice.repository.YearModelRepository;
import com.example.model.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CarService {

    CarRepository carRepository;
    CarBrandRepository carBrandRepository;
    YearModelRepository yearModelRepository;
    UserRepository userRepository;
    private CarMapper carMapper;


    public void createCar(Car car, Long userId) {

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

        UserEntity userEntity = userRepository.getById(userId);
        userEntity.getCars().add(carEntity);

        userRepository.save(userEntity);
    }

    public Car getCarByRegistrationNumber(String registrationNumber, Long userId) {
        UserEntity userEntity = userRepository.getById(userId);
        return userEntity.getCars().stream().
                filter(carEntity -> carEntity.getRegistrationNumber().equals(registrationNumber)).
                findFirst().
                map(carMapper::dtoToCar).
                orElseThrow(
                        () -> {
                                throw new RuntimeException("No such Car registered for user.");
                        }
                );

//        CarEntity carEntity = carRepository.findByRegistrationNumber(registrationNumber);
//        return carMapper.dtoToCar(carEntity);
    }

    public void updateCar(Car car, Long userId) {
        UserEntity userEntity = userRepository.getById(userId);

        userEntity.getCars().stream().
                filter(carEntity -> carEntity.getRegistrationNumber().equals(car.getRegistrationNumber())).
                forEach(carEntity -> carEntity.setAvailableSeats(car.getAvailableSeats()));

//        CarEntity carEntityFromDb = carRepository.findByRegistrationNumber(car.getRegistrationNumber());
//        carEntityFromDb.setAvailableSeats(car.getAvailableSeats());
        userRepository.save(userEntity);

    }

}

