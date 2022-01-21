package com.example.mariadbservice.mappers;

import com.example.mariadbservice.entity.LocationEntity;
import com.example.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

        LocationEntity locationToLocationDto(Location location);

        Location dtoToLocation(LocationEntity locationEntity);
}

