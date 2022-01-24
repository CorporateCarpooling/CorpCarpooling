package com.example.mariadbservice.service;

import com.example.mariadbservice.entity.*;
import com.example.mariadbservice.mappers.CarPoolMapper;
import com.example.mariadbservice.repository.*;
import com.example.model.Carpool;
import com.example.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarPoolService {

    private final CarPoolRepository carPoolRepository;
    private final CarPoolMapper carPoolMapper;
    private final CityRepository cityRepository;
    private final LocationRepository locationRepository;
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;

    public Long create(Carpool carpool, Long driverUserId) {
        Optional<UserEntity> user = userRepository.findById(driverUserId);

        if(!user.isPresent()){
            throw new RuntimeException("No user found");
        }

        CarpoolEntity carPoolEntity = carPoolMapper.carpoolToCarpoolDto(carpool);
        RouteEntity routeEntity = carPoolEntity.getRoute();

        LocationEntity startPoint = routeEntity.getStartPoint();
        startPoint.setCity(getDatabaseCity(startPoint.getCity()));

        LocationEntity finishPoint = routeEntity.getFinishPoint();
        finishPoint.setCity(getDatabaseCity(finishPoint.getCity()));

        routeEntity.setStartPoint(getDatabaseLocation(routeEntity.getStartPoint()));
        routeEntity.setFinishPoint(getDatabaseLocation(routeEntity.getFinishPoint()));

        carPoolEntity.setRoute(getDatabaseRoute(carPoolEntity.getRoute()));

        carPoolEntity.setDriver(user.get());

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