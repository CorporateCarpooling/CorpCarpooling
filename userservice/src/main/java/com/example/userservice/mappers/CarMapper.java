package com.example.userservice.mappers;

import com.example.model.Car;
import com.example.request.CarRequest;
import com.example.userservice.controller.RegisterCarRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car dtoToCar(RegisterCarRequest registerCarRequest);

    CarRequest carToCarRequest(Car car);
}
