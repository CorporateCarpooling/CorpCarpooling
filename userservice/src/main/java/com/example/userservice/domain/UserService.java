package com.example.userservice.domain;

import com.example.model.Role;
import com.example.model.User;
import com.example.userservice.clientapi.DataApi;
import com.example.userservice.controller.RegisterUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private Environment environment;
    private DataApi dataApi;
   // private webClient.Builder webClientBuilder;

    public void registerCustomer(User user) {

        Optional<User> userInDatabase = dataApi.getUserByEmail(user.getEmail());

        if(userInDatabase.isPresent()) {
            throw new RuntimeException("User already Exist");
        } else {
            user.setRoles(List.of(Role.USER));
            dataApi.postUser(user);
        }

    }

}