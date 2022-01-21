package com.example.userservice.clientapi;

import com.example.userservice.model.Car;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
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

    public Mono<Car> updateCar(Car car) {
        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));
        return webClient.put()
                .uri("/car/update")
                .body(Mono.just(car), Car.class)
                .retrieve()
                .bodyToMono(Car.class);
    }

    public void deleteCar(String regNumber) {
        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        Mono<String> postResponse = webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/car/delete")
                        .queryParam("regNumber", regNumber)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
        String response = postResponse.block();
    }

 }



