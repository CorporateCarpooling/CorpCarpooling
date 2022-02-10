package com.example.mariadbservice.mappers;

import com.example.mariadbservice.entity.CityEntity;
import com.example.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {

  @Mapping(target = "id", source = "city.id")
  @Mapping(target = "cityName", source = "city.cityName")
  CityEntity cityToCityDto(City city);

  City dtoToCity(CityEntity cityEntity);
}
