package com.example.userservice.controller;

import com.example.model.User;
import com.example.securityconfig.config.TokenProvider;
import com.example.userservice.domain.UserService;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.model.AuthToken;
import com.example.userservice.model.LoginUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private final UserMapper userMapper;

    private TokenProvider tokenProvider;

    private AuthenticationManager authenticationManager;

    @PostMapping ("/user/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        log.info("user registration{}", registerUserRequest);
        User user = userMapper.toUser(registerUserRequest);
        userService.registerCustomer(user);
        return ResponseEntity.ok("User Registered.");
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("pong");
    }

}