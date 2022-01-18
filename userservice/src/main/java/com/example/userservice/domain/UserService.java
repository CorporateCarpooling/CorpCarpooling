package com.example.userservice.domain;

import com.example.model.Role;
import com.example.model.User;
import com.example.userservice.clientapi.DataApi;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

//    private Environment environment;
    private DataApi dataApi;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

   // private webClient.Builder webClientBuilder;

    public void registerCustomer(User user) {

        Optional<User> userInDatabase = dataApi.getUserByEmail(user.getEmail());

        if(userInDatabase.isPresent()) {
            throw new RuntimeException("User already Exist");
        } else {
            user.setRoles(List.of(Role.USER));
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            dataApi.postUser(user);
        }

    }

    public void updateCustomer(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        dataApi.updateUser(user);
    }
}