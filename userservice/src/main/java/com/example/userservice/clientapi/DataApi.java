package com.example.userservice.clientapi;

import com.example.model.User;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class DataApi {

    private Environment environment;

    public Optional<User> getUserByEmail(String email) {
        // https://dzone.com/articles/resttemplate-vs-webclient
        // Tror man bör försöka använda webClient för att prata med mariadbService container.
        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        //Kolla om användaren redan finns.
        Mono<User> userInDatabase = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/user")
                        .queryParam("email", email)
                        .build())
                .retrieve()
                .bodyToMono(User.class);
        return Optional.of(userInDatabase.block());
    }

    public void postUser(User user) {
        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        Mono<String> postResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/user")
                        .build())
                .bodyValue(user)
                .retrieve()
                .bodyToMono(String.class);
        String response = postResponse.block();
    }
}
