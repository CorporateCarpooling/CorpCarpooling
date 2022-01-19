package com.example.carservice.clientapi;

import com.example.carservice.model.Route;
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

    public Optional<Route> getRouteByStartPoint(String startPoint) {

        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        Mono<Route> routeInDatabase = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/route")
                        .queryParam("start", startPoint)
                        .build())
                .retrieve()
                .bodyToMono(Route.class);
        return Optional.ofNullable(routeInDatabase.block());
    }

    public Optional<Route> getRouteByFinishPoint(String finishPoint) {

        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        Mono<Route> routeInDatabase = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/route")
                        .queryParam("finish", finishPoint)
                        .build())
                .retrieve()
                .bodyToMono(Route.class);
        return Optional.ofNullable(routeInDatabase.block());
    }
}
