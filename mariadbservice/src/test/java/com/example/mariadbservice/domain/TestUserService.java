package com.example.mariadbservice.domain;

import com.example.mariadbservice.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestUserService {

    @Autowired
    private UserService underTest;

    @DisplayName("Created user can be fetched using email with correct data.")
    @Test
    void create_and_get_saved_User_ByEmail() {
        //Given
        User user = new User();
        user.setEmail("my@mail.com");
        user.setName("my Name");
        user.setPassword("password");

        //When
        var result= underTest.create(user);

        //Then
        var userEntity = underTest.getUserByEmail("my@mail.com");
        assertEquals(result, userEntity.getId());
        assertEquals("my Name", userEntity.getName());
        assertEquals("my@mail.com", userEntity.getEmail());
        assertEquals("password", userEntity.getPassword());
    }

}