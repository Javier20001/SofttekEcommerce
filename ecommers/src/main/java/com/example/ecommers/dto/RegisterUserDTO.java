package com.example.ecommers.dto;
import com.example.ecommers.model.RoleEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A Data Transfer Object (DTO) representing the information needed to register a new user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {

    @NotNull(message = "Name must not be null")
    private String userName;
    @NotNull(message = "Password must not be null")
    private String password;
    @NotNull(message = "Email must not be null")
    private String email;
    @NotEmpty(message = "The user must have at least 1 role")
    private List<Long> roles;
}