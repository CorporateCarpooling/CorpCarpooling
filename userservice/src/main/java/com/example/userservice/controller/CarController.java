package com.example.userservice.controller;

import com.example.userservice.domain.CarService;
import com.example.userservice.mappers.CarMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;
    private CarMapper carMapper;


}