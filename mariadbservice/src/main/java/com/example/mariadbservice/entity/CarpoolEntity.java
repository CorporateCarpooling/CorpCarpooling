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
    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity user;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<RouteEntity> route;

    private LocalDateTime departureTime;
}
