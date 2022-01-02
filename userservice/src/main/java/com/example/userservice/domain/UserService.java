package com.example.userservice.domain;

import com.example.userservice.controller.RegisterUserRequest;
import com.example.userservice.model.User;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserService {

    private Environment environment;

    public void registerCustomer(User user) {

        // https://dzone.com/articles/resttemplate-vs-webclient
        // Tror man bör försöka använda webClient för att prata med mariadbService container.
        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        //Kolla om användaren redan finns.
        Mono<User> userInDatabase = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/user")
                        .queryParam("email", user.getEmail())
                        .build())
                .retrieve()
                .bodyToMono(User.class);

        //Post if no user exist
        if (userInDatabase.block() == null) {
            Mono<String> postResponse = webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/user")
                            .build())
                    .bodyValue(user)
                    .retrieve()
                    .bodyToMono(String.class);
            String response = postResponse.block();

        } else {
            //User fanns redan
        }
    }
}