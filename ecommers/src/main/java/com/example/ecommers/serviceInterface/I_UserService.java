package com.example.ecommers.serviceInterface;


import com.example.ecommers.dto.RegisterUserDTO;
import com.example.ecommers.model.UserEntity;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

public interface I_UserService {

    //Recupera un usuario por su ID
    Optional<UserEntity> getUserById(Long id);

    //Update de usuario existente
    UserEntity updateUser(Long id, UserEntity newUser);

    //Borra un usuario
    void deleteUser(Long id);

}
