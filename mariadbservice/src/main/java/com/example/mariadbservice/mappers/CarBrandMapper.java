package com.example.mariadbservice.mappers;

import com.example.mariadbservice.entity.CarBrandEntity;
import com.example.mariadbservice.model.CarBrand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarBrandMapper {

/*    CarBrandMapper INSTANCE = Mappers.getMapper(CarBrandMapper.class);

    @Mapping(target = "id", source = "carBrand.id")
    @Mapping(target = "brandName", source = "carBrand.brandName")
    CarBrandEntity carBrandToCarBrandDto(CarBrand carBrand);

    CarBrand dtoToCarBrand(CarBrandEntity carBrandEntity);

 */
}