package com.example.mariadbservice.domain;

import com.example.mariadbservice.entity.UserEntity;
import com.example.mariadbservice.mappers.UserMapper;
import com.example.model.User;
import com.example.mariadbservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * @param user
     * @return Id of the saved User
     */
    public Long create(User user) {
        var userEntity = userMapper.toEntity(user);
        return userRepository.save(userEntity).getId();
    }

    public User getUserByEmail(String userEmail) {
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        User user = userMapper.toUser(userEntity);
        return user;

    }
    public void updateUser(User user) {
        Optional<UserEntity> userEntity = userRepository.findById(user.getId());
        if (!userEntity.isPresent()) {
            throw new RuntimeException("User doesn't exsist");
        }
        UserEntity userInDatabase = userEntity.get();
        userInDatabase.setEmail(user.getEmail());
        userInDatabase.setName(user.getName());
        userInDatabase.setPassword(user.getPassword());
//        userInDatabase.setRoles(userMapper.listOfEnumToListOfEntity(user.getRoles()));
        userRepository.save(userInDatabase);

    }

    public User getUserById(String id) {
        Optional<UserEntity> userEntity = userRepository.findById(Long.parseLong(id));
        if (!userEntity.isPresent()) {
            throw new RuntimeException("User doesn't exsist");
        }
        User user = userMapper.toUser(userEntity.get());
        return user;
    }

    public void deleteUser(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (!userEntity.isPresent()) {
            throw new RuntimeException("User doesn't exsist");
        }
        userRepository.delete(userEntity.get());
    }
}