
package com.example.mariadbservice.mappers;

import com.example.mariadbservice.entity.YearModelEntity;
import com.example.mariadbservice.model.YearModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface YearModelMapper {
    /*

    YearModelMapper INSTANCE = Mappers.getMapper(YearModelMapper.class);

    YearModelEntity yearModelToYearModelEntity(YearModel yearModel);

    YearModel entityToYearModel(YearModelEntity carBrandEntity);


     */
}
