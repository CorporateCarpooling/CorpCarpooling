package com.example.mariadbservice.domain;

import com.example.mariadbservice.entity.UserEntity;
import com.example.mariadbservice.mappers.UserMapper;
import com.example.model.User;
import com.example.mariadbservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


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
}