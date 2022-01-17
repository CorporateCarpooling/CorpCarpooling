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
        log.info("CarDataApi 1 getting a car");

        Mono<Car> carInDatabase = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/car")
                        .queryParam("registrationNumber", registrationNumber)
                        .build())
                .retrieve()
                .bodyToMono(Car.class);
        log.info("CarDataApi 2 getting a car");

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
//                .uri("/car/" + car.getRegistrationNumber())
                .uri("/car/update")
//                .bodyValue(car)
                .body(Mono.just(car), Car.class)
                .retrieve()
                .bodyToMono(Car.class);
    }

/*
    public Mono<Employee> update(Employee e)
    {
        return webClient.put()
                .uri("/employees/" + e.getId())
                .body(Mono.just(e), Employee.class)
                .retrieve()
                .bodyToMono(Employee.class);
    }

 */



}
