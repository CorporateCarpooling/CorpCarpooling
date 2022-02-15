package com.example.userservice.service;

import com.example.model.*;
import com.example.userservice.clientapi.DataApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class TestUserService {

  public static final String EMAIL = "my@mail.com";
  public static final String NAME = "My Name";
  public static final String PASSWORD = "myPassword";

  @InjectMocks
  private UserService undertest;
  @Mock
  private DataApi dataApi;
  @Mock
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @DisplayName("with registration should sets roles and encryptedPassword")
  @Test
  void test_registerCustomer(){
    //Given
    User user= new User();
    user.setEmail(EMAIL);
    user.setName(NAME);
    user.setPassword(PASSWORD);

    Mockito.when(dataApi.getUserByEmail(user.getEmail())).thenReturn(Optional.empty());
    Mockito.when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("encryptedPassword");

    //When
    undertest.registerCustomer(user);

    //Then
    User expectedUser= new User();
    expectedUser.setEmail(EMAIL);
    expectedUser.setName(NAME);
    expectedUser.setPassword("encryptedPassword");
    expectedUser.setRoles(new ArrayList<Role>());
    expectedUser.getRoles().add(Role.USER);

    Mockito.verify(dataApi, Mockito.times(1)).postUser(expectedUser);

  }
  @DisplayName("should throw exception if user exists")
  @Test
  void test_registrationCustomerThrowException(){
    //Given
    String exceptionMessage= "User already Exist";
    User user= new User();
    user.setEmail(EMAIL);

    Mockito.when(dataApi.getUserByEmail(user.getEmail())).thenReturn(Optional.of(new User()));

    //when
    RuntimeException exception = assertThrows(RuntimeException.class, () -> undertest.registerCustomer(user));

    //Then
    assertEquals(exceptionMessage, exception.getMessage());

  }
  @DisplayName("Should update user with the right value")
  @Test
  void test_updateCustomer(){
    //Given
    User user= new User();
    user.setEmail(EMAIL);
    user.setName(NAME);
    user.setPassword(PASSWORD);

    Mockito.when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("encryptedPassword");

    //When
    undertest.updateCustomer(user);

    //Then
    User expectedUser= new User();
    expectedUser.setEmail(EMAIL);
    expectedUser.setName(NAME);
    expectedUser.setPassword("encryptedPassword");

    //Then
    Mockito.verify(dataApi,Mockito.times(1)).updateUser(Mockito.eq(expectedUser));
  }

  @DisplayName("Should return user by Id")
  @Test
  void test_getUser(){
    //Given
    String userId = "testId";
    User user= new User();
    user.setEmail(EMAIL);
    user.setName(NAME);
    user.setPassword(PASSWORD);
    Mockito.when(dataApi.getUserById(userId)).thenReturn(Optional.of(user));

    //When
    User response = undertest.getUser(userId);

    //Then
    User expectedUser= new User();
    expectedUser.setEmail(EMAIL);
    expectedUser.setName(NAME);
    expectedUser.setPassword(PASSWORD);

    assertEquals(expectedUser, response);
  }

  @DisplayName("Should throw exception if there is no user")
  @Test
  void test_getuserWhenThereIsNoUser(){
    //Given
    String userId = "testId";

    Mockito.when(dataApi.getUserById(userId)).thenReturn(Optional.empty());

    //when
    RuntimeException exception = assertThrows(RuntimeException.class, () -> undertest.getUser(userId));

    //Then
    String exceptionMessage= "User does not exist";
    assertEquals(exceptionMessage, exception.getMessage());
  }

  @DisplayName("should delete user by ID")
  @Test
  void test_deleteCustomer(){
    //Given
    String userId = "testId";
    Mockito.when(dataApi.getUserById(userId)).thenReturn(Optional.of(new User()));

    //when
    undertest.deleteCustomer(userId);

    //Then
    Mockito.verify(dataApi, Mockito.times(1)).deleteUser(Mockito.eq(userId));
  }

  @DisplayName("Should throw exception if there is no user")
  @Test
  void test_deleteWhenThereIsNoCustomer(){
    //Given
    String userId = "testId";

    Mockito.when(dataApi.getUserById(userId)).thenReturn(Optional.empty());

    //when
    RuntimeException exception = assertThrows(RuntimeException.class, () -> undertest.deleteCustomer(userId));

    //Then
    String exceptionMessage= "User does not exist";
    assertEquals(exceptionMessage, exception.getMessage());
  }
}