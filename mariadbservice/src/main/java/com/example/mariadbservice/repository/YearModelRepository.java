package com.example.mariadbservice.repository;

import com.example.mariadbservice.entity.YearModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearModelRepository extends JpaRepository<YearModelEntity, Long> {
    YearModelEntity findByYearModel(String yearModel);
}