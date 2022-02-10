package com.example.model;


import lombok.Data;

@Data
public class Passenger {

  private Long id;
  private Long userId;
  private Long carpoolId;
  private Boolean approved;

}

