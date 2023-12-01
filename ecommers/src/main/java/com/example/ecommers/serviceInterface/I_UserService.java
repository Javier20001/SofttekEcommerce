package com.example.ecommers.serviceInterface;


import com.example.ecommers.model.UserEntity;


import java.util.Optional;

public interface I_UserService {


    Optional<UserEntity> getUserById(Long id);

    UserEntity saveUser(UserEntity user);

    UserEntity updateUser(Long id, UserEntity newUser);

    void deleteUser(Long id);

    Optional<UserEntity> findUserByEmail(String email);
}
