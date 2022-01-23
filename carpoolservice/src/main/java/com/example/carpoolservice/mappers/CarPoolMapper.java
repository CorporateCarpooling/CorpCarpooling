package com.example.carpoolservice.mappers;

import com.example.carpoolservice.controller.RegisterRouteRequest;
import com.example.model.Carpool;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarPoolMapper {
    Carpool dtoToCarPool(RegisterRouteRequest registerRouteRequest);
}
