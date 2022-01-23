package com.example.carpoolservice.service;

import com.example.carpoolservice.clientapi.RouteApi;
import com.example.model.Carpool;
import com.example.model.Route;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarPoolService {

    private RouteApi routeApi;

   /* public RouteService() throws IOException {
        URL urlPostalCodeApi = new URL("https://api.papapi.se/lite/?query=POSTNUMMER&format=json&apikey=e70b01951a577da57446500803d2e46dfc85a6f4");
        HttpURLConnection connection = (HttpURLConnection) urlPostalCodeApi.openConnection();

        connection.setRequestMethod("GET");
    }*/

    public void registerRoute(Carpool carpool) {

//        Optional<Carpool> carPoolInDatabase = routeApi.getCarPoolByRoute(carpool.getRoute());
//
//        if (carPoolInDatabase.isPresent()) {
//            throw new RuntimeException("Car pool alerady created");
//        } else {
            routeApi.postRoute(carpool);

//        }

    }
}

//e70b01951a577da57446500803d2e46dfc85a6f4
//Api key