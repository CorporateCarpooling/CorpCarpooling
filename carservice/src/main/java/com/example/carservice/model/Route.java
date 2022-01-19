package com.example.carservice.model;

import lombok.Data;

@Data
public class Route {

    private Long id;
    private String startPoint;
    private String finishPoint;

}
