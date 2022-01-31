package com.example.bookingservice.clientapi;

import com.example.model.Carpool;
import com.example.model.User;
import com.example.request.CarPoolRequest;
import com.example.request.JoinCarpoolRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TripApi {

    private Environment environment;

    public void joinATrip(long userId, long carpoolId) {
        JoinCarpoolRequest joinCarpoolRequest = new JoinCarpoolRequest();
        joinCarpoolRequest.setCarpoolId(carpoolId);
        joinCarpoolRequest.setUserId(userId);

        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        Mono<String> postResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/join/trip")
                        .build())
                .bodyValue(joinCarpoolRequest)
                .retrieve()
                .bodyToMono(String.class);
        String response = postResponse.block();
    }
    public Optional<Carpool> getCarPoolById(Long carpoolId) {
        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        //Kolla om användaren redan finns.
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
