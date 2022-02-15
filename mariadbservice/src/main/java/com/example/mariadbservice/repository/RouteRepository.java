package com.example.mariadbservice.repository;

import com.example.mariadbservice.entity.LocationEntity;
import com.example.mariadbservice.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
  RouteEntity findByStartPointAndFinishPoint(LocationEntity startPoint, LocationEntity finishPoint);
}
