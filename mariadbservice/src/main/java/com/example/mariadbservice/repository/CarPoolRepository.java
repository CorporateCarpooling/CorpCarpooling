package com.example.mariadbservice.repository;

import com.example.mariadbservice.entity.CarpoolEntity;
import com.example.model.Carpool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CarPoolRepository extends JpaRepository<CarpoolEntity, Long> {
    CarpoolEntity findByAvailableSeatsForRide(int availableSeats);

    List<CarpoolEntity> findAllByDepartureTimeGreaterThan(LocalDateTime departureTime);

    List<CarpoolEntity> findAllByDepartureTimeGreaterThanAndDepartureTimeLessThan(LocalDateTime departureTime, LocalDateTime latestDepartureTime);
}
