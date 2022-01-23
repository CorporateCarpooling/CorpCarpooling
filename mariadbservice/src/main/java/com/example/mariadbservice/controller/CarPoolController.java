package com.example.mariadbservice.controller;

import com.example.mariadbservice.entity.CarpoolEntity;
import com.example.mariadbservice.mappers.CarPoolMapper;
import com.example.mariadbservice.service.CarPoolService;
import com.example.model.Carpool;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CarPoolController {

    private final CarPoolService carPoolService;
    private final CarPoolMapper carPoolMapper;
    @PostMapping("carpool/ride")
    public ResponseEntity<String>postRide(@RequestBody CarPoolRequest carPoolRequest){
        Carpool carpool= carPoolMapper.toCarpool(carPoolRequest);
        Long id = carPoolService.create(carpool);
       // if(carPoolMapper.carpoolToCarpoolDto(carpoolEntity.getPassengers()== null))

        return new ResponseEntity<>(Long.toString(id), HttpStatus.OK);
    }

}
