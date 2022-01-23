package com.example.mariadbservice.service;

import com.example.mariadbservice.entity.CarpoolEntity;
import com.example.mariadbservice.entity.CityEntity;
import com.example.mariadbservice.entity.LocationEntity;
import com.example.mariadbservice.entity.RouteEntity;
import com.example.mariadbservice.mappers.CarPoolMapper;
import com.example.mariadbservice.repository.CarPoolRepository;
import com.example.mariadbservice.repository.CityRepository;
import com.example.mariadbservice.repository.LocationRepository;
import com.example.mariadbservice.repository.RouteRepository;
import com.example.model.Carpool;
import com.example.model.City;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarPoolService {

    private final CarPoolRepository carPoolRepository;
    private final CarPoolMapper carPoolMapper;
    private final CityRepository cityRepository;
    private final LocationRepository locationRepository;
    private final RouteRepository routeRepository;

    public Long create(Carpool carpool) {
        CarpoolEntity carPoolEntity = carPoolMapper.carpoolToCarpoolDto(carpool);
        RouteEntity routeEntity = carPoolEntity.getRoute();

        LocationEntity startPoint = routeEntity.getStartPoint();
        startPoint.setCity(getDatabaseCity(startPoint.getCity()));

        LocationEntity finishPoint = routeEntity.getFinishPoint();
        finishPoint.setCity(getDatabaseCity(finishPoint.getCity()));

        routeEntity.setStartPoint(getDatabaseLocation(routeEntity.getStartPoint()));
        routeEntity.setFinishPoint(getDatabaseLocation(routeEntity.getFinishPoint()));

        carPoolEntity.setRoute(getDatabaseRoute(carPoolEntity.getRoute()));

        return carPoolRepository.save(carPoolEntity).getId();
    }

    private RouteEntity getDatabaseRoute(RouteEntity route) {
        RouteEntity routeInDB = routeRepository.findByStartPointAndFinishPoint(route.getStartPoint(), route.getFinishPoint());
        if(routeInDB == null){
            return routeRepository.save(route);
        }
        return routeInDB;
    }

    private LocationEntity getDatabaseLocation(LocationEntity location) {
        LocationEntity locationInDB = locationRepository.findByAddressAndCity(location.getAddress(), location.getCity());
        if(locationInDB == null){
            return locationRepository.save(location);
        }
        return locationInDB;
    }

    private CityEntity getDatabaseCity(CityEntity city) {
        CityEntity cityInDB = cityRepository.findByCityName(city.getCityName());
        if(cityInDB == null){
            return cityRepository.save(city);
        }
        return cityInDB;
    }
}
