package com.example.mariadbservice.mappers;

import com.example.mariadbservice.controller.CarRequest;
import com.example.mariadbservice.entity.CarEntity;
import com.example.mariadbservice.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "id", source = "car.id")
    @Mapping(target = "carBrand", source = "car.carBrand")
    @Mapping(target = "yearModel", source = "car.yearModel")
    @Mapping(target = "fuelType", source = "car.fuelType")
    @Mapping(target = "availableSeats", source = "car.availableSeats")
    CarEntity carToCarDto(Car car);

    Car dtoToCar(CarEntity carEntity);

    Car dtoToCar(CarRequest carRequest);

}