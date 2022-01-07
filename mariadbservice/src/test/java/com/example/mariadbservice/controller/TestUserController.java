package com.example.mariadbservice.controller;

import com.example.mariadbservice.domain.UserService;
import com.example.mariadbservice.mappers.UserMapperImpl;
import com.example.mariadbservice.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserMapperImpl.class, UserController.class})
class TestUserController {
    public static final String EMAIL = "my@mail.com";
    public static final String NAME = "My Name";
    public static final String PASSWORD = "myPassword";
    @MockBean
    private UserService userService;
    @Autowired
    private UserController underTest;

    @DisplayName("Should return ResponsEntity.ok and the right Id when posting User")
    @Test
    void test_postUser() {
        //Given
        User user = new User();
        user.setEmail(EMAIL);
        user.setName(NAME);
        user.setPassword(PASSWORD);

        Long id = 123L;
        Mockito.when(userService.create(Mockito.eq(user))).thenReturn(id);

        //when
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(EMAIL);
        userRequest.setName(NAME);
        userRequest.setPassword(PASSWORD);
        var response = underTest.postUser(userRequest);
        //Then

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("123", response.getBody());
    }
    @DisplayName("Should return ResponsEntity.ok and the right user with user in body")
    @Test
    void getUser() {
        //Given
        Long id = 123L;
        User user = new User();
        user.setEmail(EMAIL);
        user.setName(NAME);
        user.setPassword(PASSWORD);
        String email= EMAIL;
        Mockito.when(userService.getUserByEmail(email)).thenReturn(user);

        //when
        var response= underTest.getUser(email);

        //Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user,response.getBody());
    }
}