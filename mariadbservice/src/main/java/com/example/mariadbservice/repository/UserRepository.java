package com.example.mariadbservice.repository;

import com.example.mariadbservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  UserEntity findByEmail(String userEmail);
}