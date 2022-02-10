package com.example.mariadbservice.controller;

import com.example.mariadbservice.service.UserService;
import com.example.mariadbservice.mappers.UserMapperImpl;
import com.example.mariadbservice.repository.RoleRepository;
import com.example.model.Role;
import com.example.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserMapperImpl.class, UserController.class, UpdateUserRequest.class, TestUserController.TestUserControllerConfig.class})
class TestUserController {

  @TestConfiguration
  static class TestUserControllerConfig {

    @Bean
    public RoleRepository roleRepositoryBean() {
      return Mockito.mock(RoleRepository.class);
    }
  }

  public static final String EMAIL = "my@mail.com";
  public static final String NAME = "My Name";
  public static final String PASSWORD = "myPassword";
  @MockBean
  private UserService userService;
  @Autowired
  private UserController underTest;
  @Autowired
  private UpdateUserRequest updateUserRequest;

  @DisplayName("Should return ResponsEntity.ok and the right Id when posting User")
  @Test
  void test_postUser() {
    //Given
    User user = new User();
    user.setEmail(EMAIL);
    user.setName(NAME);
    user.setPassword(PASSWORD);
    user.setRoles(new ArrayList<Role>());
    user.getRoles().add(Role.USER);


    Long id = 123L;
    Mockito.when(userService.create(Mockito.eq(user))).thenReturn(id);

    //when
    UserRequest userRequest = new UserRequest();
    userRequest.setEmail(EMAIL);
    userRequest.setName(NAME);
    userRequest.setPassword(PASSWORD);
    userRequest.setRoles(new ArrayList<Role>());
    userRequest.getRoles().add(Role.USER);

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
    user.setRoles(new ArrayList<Role>());
    user.getRoles().add(Role.USER);
    String email = EMAIL;
    Mockito.when(userService.getUserByEmail(email)).thenReturn(user);

    //when
    var response = underTest.getUser(email, null);

    //Then
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(user, response.getBody());
  }

  @DisplayName("Should return ResponsEntity.ok")
  @Test
  void putUser() {
    //Given
    String message = "User updated";

    UpdateUserRequest updateUserRequest = new UpdateUserRequest();
    updateUserRequest.setEmail("my@email.com");
    updateUserRequest.setName("myName");
    updateUserRequest.setPassword("myPassword");
    updateUserRequest.setId(1l);

    //when
    var response = underTest.putUser(updateUserRequest);


    //Then
    User user = new User();
    user.setEmail("my@email.com");
    user.setName("myName");
    user.setPassword("myPassword");
    user.setId(1l);

    Mockito.verify(userService, Mockito.times(1)).updateUser(Mockito.eq(user));
    assertEquals(message, response.getBody());
    assertEquals(200, response.getStatusCodeValue());
  }

  @Test
  void deleteUser() {
    //Given
    Long id = 3005L;
    String message = "user deleted successfully";

    //When
    var response = underTest.deleteUser(id);

    //Then
    Mockito.verify(userService, Mockito.times(1)).deleteUser(id);
    assertEquals(response, ResponseEntity.ok(message));
    assertEquals(200, response.getStatusCodeValue());
  }
}