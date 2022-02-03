package com.example.mariadbservice.controller;

import com.example.mariadbservice.mappers.CarPoolMapper;
import com.example.mariadbservice.service.CarPoolService;
import com.example.model.Carpool;
import com.example.model.Passenger;
import com.example.request.CarPoolRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class CarPoolController {

    private final CarPoolService carPoolService;
    private final CarPoolMapper carPoolMapper;

    @PostMapping("carpool/ride")
    public ResponseEntity<String>postRide(@RequestBody CarPoolRequest carPoolRequest){
        Carpool carpool= carPoolMapper.toCarpool(carPoolRequest);
        Long id = carPoolService.create(carpool, carPoolRequest.getDriverUserId());
       // if(carPoolMapper.carpoolToCarpoolDto(carpoolEntity.getPassengers()== null))

        return new ResponseEntity<>(Long.toString(id), HttpStatus.OK);
    }

    @GetMapping("carpool/getcarpools")
    public ResponseEntity<List<Carpool>> getAllRides(@RequestParam(required = false) String earliestDepartureTime, @RequestParam(required = false) String latestDepartureTime){
        LocalDateTime earliestDepartureTimeLocalDateTime = earliestDepartureTime == null ? LocalDateTime.now() : LocalDateTime.parse(earliestDepartureTime);
        LocalDateTime latestDepartureTimeLocalDateTime = latestDepartureTime == null ? LocalDateTime.now().plusWeeks(1) : LocalDateTime.parse(latestDepartureTime);
        List<Carpool> carpool= carPoolService.getCarPoolByDate(earliestDepartureTimeLocalDateTime, latestDepartureTimeLocalDateTime);
        return ResponseEntity.ok(carpool);
    }
    @GetMapping("carpool/getcarpool")
    public ResponseEntity<Carpool>getCarPoolbyId(@RequestParam (required = false)Long carpoolId){
        Carpool carpool = carPoolService.getCarpoolById(carpoolId);
        return ResponseEntity.ok(carpool);

    }
    @GetMapping("passenger/getpassenger")
    public ResponseEntity<Passenger>getPassengerbyId(@RequestParam (required = false)Long passengerId) {
        Passenger passenger = carPoolService.getPassengerById(passengerId);
        return ResponseEntity.ok(passenger);
    }
    @DeleteMapping("carpool/delete")
    public ResponseEntity<String> deleteCarpool(@RequestParam Long carpoolId){
        carPoolService.deleteCarpoolById(carpoolId);
        return ResponseEntity.ok("carpool deleted");
    }

}
