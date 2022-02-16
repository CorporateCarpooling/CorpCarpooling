package com.example.mariadbservice.controller;

import com.example.mariadbservice.service.UserService;
import com.example.mariadbservice.mappers.UserMapper;
import com.example.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @PostMapping("user")
  public ResponseEntity<String> postUser(@RequestBody UserRequest userRequest) {
    User user = userMapper.toUser(userRequest);
    Long id = userService.create(user);
    return new ResponseEntity<>(Long.toString(id), HttpStatus.OK);
  }

  @GetMapping("user")
  public ResponseEntity<User> getUser(@RequestParam(required = false) String email, @RequestParam(required = false) String id) {
    User user = null;
    if (email != null)
      user = userService.getUserByEmail(email);
    else if (id != null)
      user = userService.getUserById(id);
    return ResponseEntity.ok(user);
  }

  @PatchMapping("user")
  public ResponseEntity<String> putUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
    User user = userMapper.toUser(updateUserRequest);
    userService.updateUser(user);
    return ResponseEntity.ok("User updated");
  }

  @DeleteMapping("user")
  public ResponseEntity<String> deleteUser(@RequestParam Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok("user deleted successfully");
  }
}