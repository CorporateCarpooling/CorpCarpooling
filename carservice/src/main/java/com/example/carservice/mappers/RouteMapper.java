package com.example.carservice.mappers;

import com.example.carservice.controller.RegisterRouteRequest;
import com.example.carservice.model.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    Route toRoute(RegisterRouteRequest registerRouteRequest);
}
