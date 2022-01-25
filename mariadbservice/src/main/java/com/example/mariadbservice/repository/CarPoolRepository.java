package com.example.mariadbservice.repository;

import com.example.mariadbservice.entity.CarpoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPoolRepository extends JpaRepository<CarpoolEntity, Long> {
    CarpoolEntity findByAvailableSeatsForRide(int availableSeats);
}
