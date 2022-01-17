package com.example.userservice.clientapi;

import com.example.userservice.model.Car;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarDataApi {
    private final Environment environment;

    public Optional<Car> getCarByRegistrationNumber(String registrationNumber) {

        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        Mono<Car> carInDatabase = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/car")
                        .queryParam("registrationNumber", registrationNumber)
                        .build())
                .retrieve()
                .bodyToMono(Car.class);
        return Optional.ofNullable(carInDatabase.block());
    }

    public void postCar(Car car) {
        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        Mono<String> postResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/car")
                        .build())
                .bodyValue(car)
                .retrieve()
                .bodyToMono(String.class);
        String response = postResponse.block();

    }

}
