package com.example.carpoolservice.service;

import com.example.carpoolservice.clientapi.CarDataApi;
import com.example.carpoolservice.clientapi.RouteApi;
import com.example.carpoolservice.mappers.CarPoolMapper;
import com.example.model.Car;
import com.example.model.Carpool;
import com.example.model.Route;
import com.example.request.CarPoolRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarPoolService {

    private RouteApi routeApi;
    private CarPoolMapper carPoolMapper;
    private CarDataApi carDataApi;

   /* public RouteService() throws IOException {
        URL urlPostalCodeApi = new URL("https://api.papapi.se/lite/?query=POSTNUMMER&format=json&apikey=e70b01951a577da57446500803d2e46dfc85a6f4");
        HttpURLConnection connection = (HttpURLConnection) urlPostalCodeApi.openConnection();

        connection.setRequestMethod("GET");
    }*/

    public void registerRoute(Carpool carpool, String registrationNumber, Long userId) {
        CarPoolRequest carPoolRequest = carPoolMapper.carPoolToCarPoolRequest(carpool);
        Optional<Car> carOptional = carDataApi.getCarByRegistrationNumber(registrationNumber, userId);

        if (!carOptional.isPresent()) {
            throw new RuntimeException("Car does not exist");
        } else {
            carPoolRequest.setCarId(carOptional.get().getId());
            carPoolRequest.setDriverUserId(userId);
            routeApi.postRoute(carPoolRequest);
        }

    }
}

//e70b01951a577da57446500803d2e46dfc85a6f4
//Api key