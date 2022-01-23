package com.example.mariadbservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "route")
public class RouteEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private LocationEntity startPoint;

    @ManyToOne(fetch = FetchType.EAGER)
    private LocationEntity finishPoint;

}
