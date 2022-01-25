package com.example.mariadbservice.mappers;

import com.example.mariadbservice.entity.PassengerEntity;
import com.example.model.Passenger;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PassengerMapper {

    @Autowired
    protected UserMapper userMapper;

//    @Mapping(target = "id", source = "passenger.id")
//    @Mapping(target = "user", source = "passenger.user")
//    @Mapping(target = "roles", expression = "java(listOfEnumToListOfEntity(user.getRoles()))")
    @Mapping(target = "user", expression = "java(userMapper.toEntity(passenger.getUser()))")
    public abstract PassengerEntity passengerToPassengerDto(Passenger passenger);

    @Mapping(target = "user", expression = "java(userMapper.toUser(passengerEntity.getUser()))")
    public abstract Passenger dtoToPassenger(PassengerEntity passengerEntity);

}
