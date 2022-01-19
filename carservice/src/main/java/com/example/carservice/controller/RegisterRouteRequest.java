package com.example.carservice.controller;

import lombok.Data;

@Data
public class RegisterRouteRequest {

    private String addressStartPoint;
    private String addressFinishPoint;
    private String cityStartPoint;
    private String cityFinishPoint;
}
