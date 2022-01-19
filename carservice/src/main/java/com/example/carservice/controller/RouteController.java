package com.example.carservice.controller;

import com.example.carservice.domain.RouteService;
import com.example.carservice.mappers.RouteMapper;
import com.example.carservice.model.Route;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class RouteController {

    private RouteService routeService;
    private RouteMapper routeMapper;

    @PostMapping("/route/register")
    public ResponseEntity<String> registerRoute(@RequestBody RegisterRouteRequest registerRouteRequest) {
        Route route = routeMapper.toRoute(registerRouteRequest);
        //  routeService.registerRoute(route);
        return ResponseEntity.ok("Route registered");
    }

    @GetMapping("/route")
    public ResponseEntity<List<Route>> getRoutes(@RequestParam String fromPoint, @RequestParam String toPoint) {
        return ResponseEntity.ok(new ArrayList<>());
    }


}
