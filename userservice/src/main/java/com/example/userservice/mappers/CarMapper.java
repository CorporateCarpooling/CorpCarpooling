package com.example.userservice.mappers;

import com.example.userservice.controller.RegisterCarRequest;
import com.example.userservice.model.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car dtoToCar(RegisterCarRequest registerCarRequest);
}
