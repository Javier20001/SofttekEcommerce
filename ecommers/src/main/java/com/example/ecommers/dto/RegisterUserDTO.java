package com.example.ecommers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @NotEmpty
    @NotNull(message = "Name must not be null")
    private String userName;
    @NotNull(message = "Password must not be null")
    @NotBlank
    @NotEmpty
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    @NotBlank
    @NotEmpty
    @NotNull(message = "Email must not be null")
    private String email;
    @NotEmpty
    @NotEmpty(message = "The user must have at least 1 role")
    private List<Long> roles;
}