package com.example.mariadbservice.mappers;

import com.example.mariadbservice.controller.UserRequest;
import com.example.mariadbservice.entity.RoleEntity;
import com.example.mariadbservice.entity.UserEntity;
import com.example.model.Role;
import com.example.model.User;
import com.example.mariadbservice.repository.RoleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = {UserEntity.class, User.class}, uses= {UserEntity.class, User.class}, componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected RoleRepository roleRepository;

    //UserMapper Instance = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "roles", expression = "java(listOfEnumToListOfEntity(user.getRoles()))")
    public abstract UserEntity toEntity(User user);

    public abstract User toUser(UserRequest userRequest);

    @Mapping(target = "roles", expression = "java(listOfEntityToListOfEnum(userEntity.getRoles()))")
    public abstract User toUser(UserEntity userEntity);
    //User toUser(UserEntity savedEntity);

    protected final List<Role> listOfEntityToListOfEnum(List<RoleEntity> roles) {
        return roles.stream()
                .map(roleEntity -> Role.valueOf(roleEntity.getName()))
                .collect(Collectors.toList());
    }

    protected final List<RoleEntity> listOfEnumToListOfEntity(List<Role> roles) {
        return roles.stream()
                .map(this::findOrCreateRole)
                .collect(Collectors.toList());
    }

    private RoleEntity findOrCreateRole(Role role) {
        RoleEntity roleEntity = roleRepository.findByName(role.name());
        if (roleEntity == null) {
            roleEntity = new RoleEntity();
            roleEntity.setName(role.name());
            roleEntity = roleRepository.save(roleEntity);
        }
        return roleEntity;
    }
}
