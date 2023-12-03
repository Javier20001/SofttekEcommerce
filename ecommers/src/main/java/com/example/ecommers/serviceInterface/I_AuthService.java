package com.example.ecommers.serviceInterface;

import com.example.ecommers.dto.RegisterUserDTO;
import com.example.ecommers.model.UserEntity;

import java.util.Optional;

public interface I_AuthService {
    public RegisterUserDTO save(RegisterUserDTO user) throws RuntimeException;
    public Optional<UserEntity> findByEmail(String email);
    void resetRequest(String email);
    void resetPassword(String token, String password);
}
