package com.example.mariadbservice.mappers;

import com.example.mariadbservice.entity.PassengerEntity;
import com.example.mariadbservice.repository.CarPoolRepository;
import com.example.mariadbservice.repository.UserRepository;
import com.example.model.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PassengerMapper {

  @Autowired
  protected UserMapper userMapper;
  @Autowired
  protected UserRepository userRepository;
  @Autowired
  protected CarPoolRepository carPoolRepository;

  @Mapping(target = "carpool", expression = "java(carPoolRepository.getById(passenger.getCarpoolId()))")
  @Mapping(target = "user", expression = "java(userRepository.getById(passenger.getUserId()))")
  public abstract PassengerEntity passengerToPassengerDto(Passenger passenger);

  @Mapping(target = "userId", source = "passengerEntity.user.id")
  @Mapping(target = "carpoolId", source = "passengerEntity.carpool.id")
  public abstract Passenger dtoToPassenger(PassengerEntity passengerEntity);
}
