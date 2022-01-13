package com.example.userservice.domain;

import com.example.userservice.model.Car;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class CarService {

    private Environment environment;

    public void registerCar(Car car) {
        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        Mono<Car> carInDatabase = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/car")
                        .queryParam("carbrand", car.getCarBrand())
                        .queryParam("registrationNumber", car.getRegistrationNumber())
                        .queryParam("year", car.getYearModel())
                        .queryParam("fueltype", car.getFuelType())
                        .queryParam("seats", car.getAvailableSeats())
                        .queryParam("price", car.getPricePerPassenger())
                        .build())
                .retrieve()
                .bodyToMono(Car.class);

        if (carInDatabase.block() == null) {
            Mono<String> postResponse = webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/car")
                            .build())
                    .bodyValue(car)
                    .retrieve()
                    .bodyToMono(String.class);
            String response = postResponse.block();

        } else {
            throw new RuntimeException("Car already exist");
        }


    }


}

