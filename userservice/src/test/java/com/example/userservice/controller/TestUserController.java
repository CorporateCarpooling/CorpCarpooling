package com.example.userservice.controller;

import com.example.securityconfig.config.TokenProvider;
import com.example.userservice.domain.UserService;
import com.example.userservice.mappers.UserMapperImpl;
import com.example.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserMapperImpl.class, UserController.class})
class TestUserController {
    public static final String EMAIL = "my@mail.com";
    public static final String NAME = "My Name";
    public static final String PASSWORD = "myPassword";

    @Autowired
    private UserController underTest;
    @MockBean
    private UserService userService;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    TokenProvider tokenProvider;

    @Mock
    Principal principal;

    @DisplayName("Should return ResponsEntity.ok when registerd")
    @Test
    void test_registerUser() {
        //Given
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail(EMAIL);
        registerUserRequest.setName(NAME);
        registerUserRequest.setPassword(PASSWORD);

        //When
        var response = underTest.registerUser(registerUserRequest);

        //Then
        User expectedUser = new User();
        expectedUser.setEmail(EMAIL);
        expectedUser.setName(NAME);
        expectedUser.setPassword(PASSWORD);

        Mockito.verify(userService, Mockito.times(1)).registerCustomer(eq(expectedUser));
        assertEquals(200,response.getStatusCodeValue());

    }
    @Test
    void test_updateUser(){
        //Given
        UpdateUserRequest updateUserRequest= new UpdateUserRequest();
        updateUserRequest.setEmail(EMAIL);
        updateUserRequest.setName(NAME);
        updateUserRequest.setPassword(PASSWORD);

        Mockito.when(principal.getName()).thenReturn("3005");

        //when
        var response = underTest.updateUser(principal, updateUserRequest);

        //Then
        User userUpdated= new User();
        userUpdated.setId(3005L);
        userUpdated.setName(NAME);
        userUpdated.setEmail(EMAIL);
        userUpdated.setPassword(PASSWORD);

        Mockito.verify(userService,Mockito.times(1)).updateCustomer(eq(userUpdated));

    }
}