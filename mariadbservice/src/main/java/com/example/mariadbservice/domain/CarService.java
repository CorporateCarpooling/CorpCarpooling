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
        var carEntity = carMapper.carToCarDto(car);
        CarEntity carEntityFromDb = carRepository.findByRegistrationNumber(registrationNumber);
        System.out.println(carEntityFromDb.toString());
        CarBrandEntity carBrandEntity = carBrandRepository.findByBrandName(carEntity.getCarBrand().getBrandName());
        if (carEntity.getCarBrand().equals(carBrandEntity)) {
            carEntityFromDb.setCarBrand(carEntity.getCarBrand());
        } else {
            carBrandEntity = carBrandRepository.save(carEntity.getCarBrand());
            carEntityFromDb.setCarBrand(carEntity.getCarBrand());

        }
//        carEntityFromDb.setYearModel(carEntity.getYearModel());
        carEntityFromDb.setAvailableSeats(carEntity.getAvailableSeats());
        carEntityFromDb.setFuelType(carEntity.getFuelType());
        carEntityFromDb.setPrice(carEntity.getPrice());

        return carRepository.save(carEntityFromDb).toString();;
    }


/*
    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

 */

}

