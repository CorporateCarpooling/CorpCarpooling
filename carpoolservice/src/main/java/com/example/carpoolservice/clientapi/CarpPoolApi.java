package com.example.carpoolservice.clientapi;

import com.example.model.Carpool;
import com.example.model.Route;
import com.example.request.CarPoolRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CarpPoolApi {

  private Environment environment;

  public Optional<Carpool> getCarPoolByRoute(Route route) {

    WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

    Mono<Carpool> carPoolInDatabase = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/route")
            .queryParam("route", route)
            .build())
        .retrieve()
        .bodyToMono(Carpool.class);
    return Optional.ofNullable(carPoolInDatabase.block());
  }

  public void postRoute(CarPoolRequest carPoolRequest) {
    WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

    Mono<String> postResponse = webClient.post()
        .uri(uriBuilder -> uriBuilder
            .path("/carpool/ride")
            .build())
        .bodyValue(carPoolRequest)
        .retrieve()
        .bodyToMono(String.class);
    String response = postResponse.block();
  }

  public List<Carpool> getCarPoolByDate(String earliestDepartureTimeLocalDateTime, String latestDepartureTimeLocalDateTime) {
    WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

    Flux<Carpool> carpoolsInDataBase = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("carpool/getcarpools")
            .queryParamIfPresent("earliestDepartureTime", Optional.ofNullable(earliestDepartureTimeLocalDateTime))
            .queryParamIfPresent("latestDepartureTime", Optional.ofNullable(latestDepartureTimeLocalDateTime))
            .build())
        .retrieve()
        .bodyToFlux(Carpool.class);
    return carpoolsInDataBase.collectList().block();
  }

  public void deleteCarpoolById(Long carpoolId) {
    WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));
    Mono<String> postResponse = webClient.delete()
        .uri(uriBuilder -> uriBuilder
            .path("carpool/delete")
            .queryParam("carpoolId", carpoolId)
            .build())
        .retrieve()
        .bodyToMono(String.class); //har string som param. och retunerar string
    String response = postResponse.block(); //väntar på response
  }

  public Optional<Carpool> getCarPoolById(Long carpoolId) {
    WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

    Mono<Carpool> carpoolInDatabase = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("carpool/getcarpool")
            .queryParam("carpoolId", carpoolId)
            .build())
        .retrieve()
        .bodyToMono(Carpool.class);
    return Optional.ofNullable(carpoolInDatabase.block());
  }
}
