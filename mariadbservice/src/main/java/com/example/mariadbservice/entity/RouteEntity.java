package com.example.mariadbservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class RouteEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne (fetch = FetchType.EAGER)
    private StartPointEntity startPointId;

    @ManyToOne (fetch = FetchType.EAGER)
    private FinishPointEntity finishPointId;

}
