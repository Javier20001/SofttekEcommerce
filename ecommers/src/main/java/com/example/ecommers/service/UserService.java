package com.example.ecommers.service;


import com.example.ecommers.model.UserEntity;
import com.example.ecommers.repository.I_UserRepository;
import com.example.ecommers.serviceInterface.I_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements I_UserService {

    @Autowired
    private I_UserRepository userRepository;

    //Recupera un usuario por su ID
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    //Guarda un usuario
    public UserEntity saveUser(UserEntity user) {
        UserEntity userEntity = saveUser(user);
        user.setStatus(true);
        return userRepository.save(userEntity);
    }
    //Update de usuario existente
    public UserEntity updateUser(Long id, UserEntity newUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUserName(newUser.getUserName());
                    existingUser.setEmail(newUser.getEmail());
                    existingUser.setPassword(newUser.getPassword());
                    existingUser.setRoles(newUser.getRoles());
                    existingUser.setStatus(newUser.isStatus());
                    existingUser = saveUser(newUser);
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("Id User " + id + " not found"));
    }

    //Borra un usuario
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setStatus(false);
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("Id user \" + id + \" not found"));
    }

    //Envia un email para recuperar la contraseña
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }



}
