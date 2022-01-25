package com.example.mariadbservice.mappers;

import com.example.mariadbservice.entity.RouteEntity;
import com.example.model.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {

        RouteEntity routeToRouteDto(Route route);

        Route dtoToRoute(RouteEntity routeEntity);

}
