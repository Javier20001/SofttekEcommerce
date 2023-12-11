package com.example.ecommers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * A Data Transfer Object (DTO) representing the login credentials of a user.
 */
@Data
public class LoginUserDTO {

    /**
     * The username for authentication.
     */
    @NotNull (message = "Username must not be null")
    @Email(message = "Must be a valid email address")
    @NotBlank(message = "Email is required")
    private String email;

    /**
     * The password for authentication.
     */
    @NotNull (message = "Password must not be null")
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}