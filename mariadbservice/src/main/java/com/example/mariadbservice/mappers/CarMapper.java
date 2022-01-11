package com.example.mariadbservice.mappers;

import com.example.mariadbservice.controller.CarRequest;
import com.example.mariadbservice.entity.CarEntity;
import com.example.mariadbservice.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
//    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(target = "id", source = "car.id")
    @Mapping(target = "carBrand", source = "car.carBrand")
    @Mapping(target = "yearModel", source = "car.yearModel")
    @Mapping(target = "fuelType", source = "car.fuelType")
    @Mapping(target = "availableSeats", source = "car.availableSeats")
    @Mapping(target = "price", source = "car.pricePerPassenger")
    CarEntity carToCarDto(Car car);

    @Mapping(target = "pricePerPassenger", source = "carEntity.price")
    Car dtoToCar(CarEntity carEntity);

    Car dtoToCar(CarRequest carRequest);

    List<CarEntity> carsToCarDTOs(List<Car> cars);

//    Car RequestToCar(CarRegistrationRequest carRegistrationRequest);


}