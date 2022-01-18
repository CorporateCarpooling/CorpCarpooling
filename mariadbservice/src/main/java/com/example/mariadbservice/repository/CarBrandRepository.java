package com.example.mariadbservice.repository;

import com.example.mariadbservice.entity.CarBrandEntity;
import com.example.mariadbservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrandEntity, Long> {
    CarBrandEntity findByBrandName(String brandName);

}