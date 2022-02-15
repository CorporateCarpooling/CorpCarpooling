package com.example.carpoolservice.clientapi;

import com.example.model.Car;
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

  public Optional<Car> getCarByRegistrationNumber(String registrationNumber, Long userId) {

    WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

    Mono<Car> carInDatabase = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/car")
            .queryParam("registrationNumber", registrationNumber)
            .queryParam("userId", userId)
            .build())
        .retrieve()
        .bodyToMono(Car.class);

    return Optional.ofNullable(carInDatabase.block());
  }
}
