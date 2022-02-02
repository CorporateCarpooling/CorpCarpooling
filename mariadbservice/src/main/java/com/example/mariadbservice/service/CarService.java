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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarService {

   private CarRepository carRepository;
   private CarBrandRepository carBrandRepository;
   private YearModelRepository yearModelRepository;
   private UserRepository userRepository;
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
        carEntity.setUser(userEntity);

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
    }

    public List<Car> getAllCars(Long userId) {
        UserEntity userEntity = userRepository.getById(userId);
        return userEntity.getCars().stream()
                .map(carMapper::dtoToCar).collect(Collectors.toList());
    }

    public void updateCar(Car car, Long userId) {
        UserEntity userEntity = userRepository.getById(userId);

        userEntity.getCars().stream().
                filter(carEntity -> carEntity.getRegistrationNumber().equals(car.getRegistrationNumber())).
                forEach(carEntity -> carEntity.setAvailableSeats(car.getAvailableSeats()));

        userRepository.save(userEntity);

    }

    @Transactional
    public void deleteCarByRegistrationNumber(String regNumber, Long userId) {
        UserEntity userEntity = userRepository.getById(userId);

        Optional<CarEntity> carToRemove = userEntity.getCars().stream().
                filter(carEntity -> carEntity.getRegistrationNumber().equals(regNumber)).
                findFirst();

        if (!carToRemove.isPresent()) {
            throw new RuntimeException("Car doesn't exist");
        }

        /**
         * TODO*: Ta bort alla carpools som använder bilen som ska tas bort , Om annan User använder samma bil?
         */

        userEntity.getCars().remove(carToRemove.get());
        userRepository.saveAndFlush(userEntity);
    }

}
