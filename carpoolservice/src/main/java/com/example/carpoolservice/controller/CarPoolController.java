package com.example.carpoolservice.controller;

import com.example.carpoolservice.mappers.CarPoolMapper;
import com.example.carpoolservice.service.CarPoolService;
import com.example.model.Carpool;
import com.example.model.Route;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class CarPoolController {

    private CarPoolService carPoolService;
    private CarPoolMapper carPoolMapper;

    @PostMapping("/route/register")
    public ResponseEntity<String> registerRoute(@RequestBody RegisterRouteRequest registerRouteRequest) {
        Carpool carpool = carPoolMapper.dtoToCarPool(registerRouteRequest);
        carPoolService.registerRoute(carpool);
        return ResponseEntity.ok("Route registered");
    }

    @GetMapping("/route")
    public ResponseEntity<List<Route>> getRoutes(@RequestParam String fromPoint, @RequestParam String toPoint) {
        return ResponseEntity.ok(new ArrayList<>());
    }


}
