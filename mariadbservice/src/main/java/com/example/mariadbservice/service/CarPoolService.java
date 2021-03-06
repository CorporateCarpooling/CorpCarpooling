package com.example.mariadbservice.service;

import com.example.mariadbservice.entity.*;
import com.example.mariadbservice.mappers.CarPoolMapper;
import com.example.mariadbservice.mappers.PassengerMapper;
import com.example.mariadbservice.repository.*;
import com.example.model.Carpool;
import com.example.model.Passenger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarPoolService {

  private final CarPoolRepository carPoolRepository;
  private final CarPoolMapper carPoolMapper;
  private final CityRepository cityRepository;
  private final LocationRepository locationRepository;
  private final RouteRepository routeRepository;
  private final UserRepository userRepository;
  private final PassengerRepository passengerRepository;
  private final PassengerMapper passengerMapper;

  public Long create(Carpool carpool, Long driverUserId) {
    Optional<UserEntity> user = userRepository.findById(driverUserId);

    if (!user.isPresent()) {
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
    if (routeInDB == null) {
      return routeRepository.save(route);
    }
    return routeInDB;
  }

  private LocationEntity getDatabaseLocation(LocationEntity location) {
    LocationEntity locationInDB = locationRepository.findByAddressAndCity(location.getAddress(), location.getCity());
    if (locationInDB == null) {
      return locationRepository.save(location);
    }
    return locationInDB;
  }

  private CityEntity getDatabaseCity(CityEntity city) {
    CityEntity cityInDB = cityRepository.findByCityName(city.getCityName());
    if (cityInDB == null) {
      return cityRepository.save(city);
    }
    return cityInDB;
  }

  public List<Carpool> getCarPoolByDate(LocalDateTime departureTime, LocalDateTime latestDepartureTime) {
    List<CarpoolEntity> carpoolEntities = carPoolRepository.findAllByDepartureTimeGreaterThanAndDepartureTimeLessThan(departureTime, latestDepartureTime);
    List<Carpool> carpools = carpoolEntities.stream().map(carPoolMapper::dtoToCarpool).collect(Collectors.toList());
    return carpools;

  }

  public Carpool getCarpoolById(Long carpoolId) {
    Optional<CarpoolEntity> carpoolEntity = carPoolRepository.findById(carpoolId);
    if (!carpoolEntity.isPresent()) {
      throw new RuntimeException("There is no carpool");
    }
    Carpool carpool = carPoolMapper.dtoToCarpool(carpoolEntity.get());
    return carpool;
  }

  public Passenger getPassengerById(Long passengerId) {
    Optional<PassengerEntity> passengerEntity = passengerRepository.findById(passengerId);
    if (!passengerEntity.isPresent()) {
      throw new RuntimeException("There is no passenger");
    }
    Passenger passenger = passengerMapper.dtoToPassenger(passengerEntity.get());
    return passenger;
  }

  public void deleteCarpoolById(Long carpoolId) {
    Optional<CarpoolEntity> carpoolEntity = carPoolRepository.findById(carpoolId);

    if (!carpoolEntity.isPresent()) {
      throw new RuntimeException("Carpool doesn't exist");
    }
    carPoolRepository.delete(carpoolEntity.get());
  }
}
