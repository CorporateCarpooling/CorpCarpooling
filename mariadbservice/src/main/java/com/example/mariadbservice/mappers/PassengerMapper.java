package com.example.mariadbservice.mappers;

import com.example.mariadbservice.entity.PassengerEntity;
import com.example.model.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
//    @Mapping(target = "id", source = "passenger.id")
//    @Mapping(target = "user", source = "passenger.user")
//    @Mapping(target = "roles", expression = "java(listOfEnumToListOfEntity(user.getRoles()))")
//    PassengerEntity passengerToPassengerDto(Passenger passenger);

//    Passenger dtoToPassenger(PassengerEntity passengerEntity);

}
