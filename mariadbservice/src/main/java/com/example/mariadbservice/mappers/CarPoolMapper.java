package com.example.mariadbservice.mappers;

import com.example.request.CarPoolRequest;
import com.example.mariadbservice.entity.CarpoolEntity;
import com.example.mariadbservice.entity.PassengerEntity;
import com.example.mariadbservice.repository.CarRepository;
import com.example.model.Car;
import com.example.model.Carpool;
import com.example.model.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = {ArrayList.class, Passenger.class}, componentModel = "spring")
//@AllArgsConstructor
public abstract class CarPoolMapper {

        @Autowired
        private PassengerMapper passengerMapper;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

//        @Mapping(target = "id", source = "car.id")
//        @Mapping(target = "carBrand", source = "car.carBrand")
//        @Mapping(target = "yearModel", source = "car.yearModel")
//        @Mapping(target = "fuelType", source = "car.fuelType")
//        @Mapping(target = "availableSeats", source = "car.availableSeats")
   @Mapping(target = "passengers", expression = "java(toPassengerEntities(carPool.getPassengers()))")
   public abstract CarpoolEntity carpoolToCarpoolDto(Carpool carPool);

   @Mapping(target = "passengers", expression = "java(toPassenger(carPoolEntity.getPassengers()))")
   @Mapping(target = "driverId", source = "carPoolEntity.driver.id")
   public abstract Carpool dtoToCarpool(CarpoolEntity carPoolEntity);

   @Mapping(target = "car", expression = "java(getCarFromId(carPoolRequest.getCarId()))")
   @Mapping(target = "passengers", expression = "java(new ArrayList<Passenger>())")
   public abstract Carpool toCarpool(CarPoolRequest carPoolRequest);

   protected Car getCarFromId(Long carId) {
       return carMapper.dtoToCar(carRepository.getById(carId));
   }
//        Carpool dtoToCarpool(CarpoolRequest carpoolRequest);
    protected List<PassengerEntity> toPassengerEntities(List<Passenger> passengers) {
        return passengers.stream().map(passengerMapper::passengerToPassengerDto).collect(Collectors.toList());
    }

    protected List<Passenger> toPassenger(List<PassengerEntity> passengers) {
        return passengers.stream().map(passengerMapper::dtoToPassenger).collect(Collectors.toList());
    }

}
