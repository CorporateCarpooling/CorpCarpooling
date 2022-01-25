package com.example.carpoolservice.clientapi;

import com.example.model.Carpool;
import com.example.model.Route;
import com.example.request.CarPoolRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RouteApi {

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

/*    public Optional<Route> getRouteByFinishPoint(String finishPoint) {

        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        Mono<Route> routeInDatabase = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/route")
                        .queryParam("finish", finishPoint)
                        .build())
                .retrieve()
                .bodyToMono(Route.class);
        return Optional.ofNullable(routeInDatabase.block());
    }*/

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
}
