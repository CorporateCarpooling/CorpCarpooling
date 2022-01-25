package com.example.mariadbservice.repository;

import com.example.mariadbservice.entity.CityEntity;
import com.example.mariadbservice.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    LocationEntity findByAddressAndCity(String address, CityEntity city);
}
