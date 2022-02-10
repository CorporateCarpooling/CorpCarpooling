package com.example.userservice.service;

import com.example.model.Role;
import com.example.model.User;
import com.example.userservice.clientapi.DataApi;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

  private DataApi dataApi;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public void registerCustomer(User user) {

    Optional<User> userInDatabase = dataApi.getUserByEmail(user.getEmail());

    if (userInDatabase.isPresent()) {
      throw new RuntimeException("User already Exist");
    } else {
      user.setRoles(List.of(Role.USER));
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      dataApi.postUser(user);
    }
  }

  public void updateCustomer(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    dataApi.updateUser(user);
  }

  public User getUser(String userId) {
    Optional<User> userInDatabase = dataApi.getUserById(userId);

    if (userInDatabase.isPresent()) {
      return userInDatabase.get();
    } else {
      throw new RuntimeException("User does not exist");
    }
  }

  public void deleteCustomer(String id) {
    dataApi.deleteUser(id);
  }
}
