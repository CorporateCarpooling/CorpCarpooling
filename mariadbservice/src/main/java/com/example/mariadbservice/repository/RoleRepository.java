package com.example.mariadbservice.repository;

import com.example.mariadbservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

  public RoleEntity findByName(String name);
}
