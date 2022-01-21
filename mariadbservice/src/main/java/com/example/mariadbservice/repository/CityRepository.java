package com.example.mariadbservice.repository;

import com.example.mariadbservice.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
}
