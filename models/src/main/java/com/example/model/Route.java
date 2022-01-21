package com.example.model;

import lombok.Data;

@Data
public class Route {

    private Long id;
    private Location startPointId;
    private Location finishPointId;

}
