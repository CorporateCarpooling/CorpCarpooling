package com.example.mariadbservice.mappers;

import com.example.mariadbservice.entity.CarpoolEntity;
import com.example.mariadbservice.entity.PassengerEntity;
import com.example.model.Carpool;
import com.example.model.Passenger;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
//@AllArgsConstructor
public abstract class CarPoolMapper {

        @Autowired
        private PassengerMapper passengerMapper;

//        @Mapping(target = "id", source = "car.id")
//        @Mapping(target = "carBrand", source = "car.carBrand")
//        @Mapping(target = "yearModel", source = "car.yearModel")
//        @Mapping(target = "fuelType", source = "car.fuelType")
//        @Mapping(target = "availableSeats", source = "car.availableSeats")
   @Mapping(target = "passengers", expression = "java(toPassengerEntities(carPool.getPassengers()))")
   public abstract CarpoolEntity carpoolToCarpoolDto(Carpool carPool);

   @Mapping(target = "passengers", expression = "java(toPassenger(carPoolEntity.getPassengers()))")
   public abstract Carpool dtoToCarpool(CarpoolEntity carPoolEntity);

//        Carpool dtoToCarpool(CarpoolRequest carpoolRequest);
    protected List<PassengerEntity> toPassengerEntities(List<Passenger> passengers) {
        return passengers.stream().map(passengerMapper::passengerToPassengerDto).collect(Collectors.toList());
    }

    protected List<Passenger> toPassenger(List<PassengerEntity> passengers) {
        return passengers.stream().map(passengerMapper::dtoToPassenger).collect(Collectors.toList());
    }

}
