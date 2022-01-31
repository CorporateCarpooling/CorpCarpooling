package com.example.model;


import lombok.Data;

@Data
public class Passenger {

    private Long id;
    private User user;
    private Carpool carpool;
    private Boolean approved;

}

