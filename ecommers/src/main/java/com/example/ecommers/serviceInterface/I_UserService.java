package com.example.ecommers.serviceInterface;


import com.example.ecommers.model.UserEntity;


import java.util.Optional;

public interface I_UserService {


    //Recupera un usuario por su ID
    Optional<UserEntity> getUserById(Long id);

    //Guarda un usuario
    UserEntity saveUser(UserEntity user);

    //Update de usuario existente
    UserEntity updateUser(Long id, UserEntity newUser);

    //Borra un usuario
    void deleteUser(Long id);

    //Envia un email para recuperar la contraseña
    Optional<UserEntity> findUserByEmail(String email);
}
