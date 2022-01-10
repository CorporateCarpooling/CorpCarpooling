package com.example.mariadbservice.domain;


import com.example.mariadbservice.mappers.CarMapper;
import com.example.mariadbservice.model.Car;
import com.example.mariadbservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CarService {

    private CarRepository carRepository;
    private CarMapper carMapper;

    public Long createCar(Car car) {
        var carEntity = carMapper.carToCarDto(car);
        System.out.println("in service class");
        return carRepository.save(carEntity).getId();
    }
/*
    public List<Car> findAll() {

        return carRepository.findAll();
    }


 */
 /*
    public void registerCar(CarRegistrationRequest request) {
        // change to mapper
        Car car = Car.builder()
                .carBrandId(request.getCarBrandId())
                .registrationNumber(request.getRegistrationNumber())
                .yearModelId(request.getYearModelId())
                .build();

        // store car in db
        carRepository.save(car);

  */

}

