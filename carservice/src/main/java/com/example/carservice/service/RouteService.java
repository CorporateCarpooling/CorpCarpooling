package com.example.carservice.service;

import com.example.carservice.clientapi.RouteApi;
import com.example.carservice.model.Route;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RouteService {
/*
    private RouteApi routeApi;

    public RouteService() throws IOException {
        URL urlPostalCodeApi = new URL("https://api.papapi.se/lite/?query=POSTNUMMER&format=json&apikey=e70b01951a577da57446500803d2e46dfc85a6f4");
        HttpURLConnection connection = (HttpURLConnection) urlPostalCodeApi.openConnection();

        connection.setRequestMethod("GET");
    }

    public void registerRoute(Route route) {

        Optional<Route> routeInDatabase = routeApi.getRouteByStartPoint(route.getStartPoint());

        if (routeInDatabase.isPresent()) {
            route.getId();
        } else {

        }

    }*/
}

//e70b01951a577da57446500803d2e46dfc85a6f4
//Api key