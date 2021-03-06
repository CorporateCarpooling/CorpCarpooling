package com.example.mariadbservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "carpool")
public class CarpoolEntity {
  @Id
  @GeneratedValue
  private Long id;

  @OneToOne(fetch = FetchType.EAGER)
  private CarEntity car;

  @ManyToOne(fetch = FetchType.EAGER)
  private RouteEntity route;

  @OneToMany(mappedBy = "carpool", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PassengerEntity> passengers;

  private LocalDateTime departureTime;

  private int availableSeatsForRide;
  private double pricePerRide;

  @ManyToOne
  private UserEntity driver;
}
