package com.example.securityconfig.clientapi;

import com.example.model.User;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class SecurityApi implements UserDetailsService {

    private Environment environment;

    private Optional<User> getUserByEmail(String email) {
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
        return Optional.ofNullable(userInDatabase.block());
    }

    private Optional<User> getUserById(String id) {
        // https://dzone.com/articles/resttemplate-vs-webclient
        // Tror man bör försöka använda webClient för att prata med mariadbService container.
        WebClient webClient = WebClient.create(environment.getProperty("mariadbservice.host"));

        //Kolla om användaren redan finns.
        Mono<User> userInDatabase = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/user")
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .bodyToMono(User.class);
        return Optional.ofNullable(userInDatabase.block());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = username.contains("@") ? getUserByEmail(username) : getUserById(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(Long.toString(optionalUser.get().getId()), optionalUser.get().getPassword(), getAuthority(optionalUser.get()));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        });
        return authorities;
    }
}
