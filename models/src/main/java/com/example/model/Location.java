package com.example.model;

import lombok.Data;

@Data
public class Location {

  private Long id;
  private String address;
  private City city;
}

