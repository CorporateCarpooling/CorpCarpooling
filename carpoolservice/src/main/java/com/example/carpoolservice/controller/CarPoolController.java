package com.example.carpoolservice.controller;

import com.example.carpoolservice.mappers.CarPoolMapper;
import com.example.carpoolservice.service.CarPoolService;
import com.example.model.Carpool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class CarPoolController {

  private CarPoolService carPoolService;
  private CarPoolMapper carPoolMapper;

  @PostMapping("/route/register")
  public ResponseEntity<String> registerRoute(Principal principal, @RequestBody RegisterRouteRequest registerRouteRequest) {
    Carpool carpool = carPoolMapper.dtoToCarPool(registerRouteRequest);
    carPoolService.registerRoute(carpool, registerRouteRequest.getRegistrationNumber(), Long.parseLong(principal.getName()));
    return ResponseEntity.ok("Route registered");
  }

  @GetMapping("/carpools")
  public ResponseEntity<List<Carpool>> getAllCarPools(@RequestParam(required = false) String earliestDepartureTime, @RequestParam(required = false) String latestDepartureTime) {
    List<Carpool> carpools = carPoolService.getCarPools(earliestDepartureTime, latestDepartureTime);
    return ResponseEntity.ok(carpools);
  }

  @DeleteMapping("carpool/delete")
  public ResponseEntity<String> deleteCarpool(Principal principal, @RequestParam Long carpoolId) {
    carPoolService.deleteCarpool(carpoolId, Long.parseLong(principal.getName()));
    return ResponseEntity.ok("Carpool deleted");
  }
}
