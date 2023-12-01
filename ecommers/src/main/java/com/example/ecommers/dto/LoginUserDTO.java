package com.example.ecommers.dto;

import jakarta.validation.constraints.NotNull;
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
    private String email;

    /**
     * The password for authentication.
     */
    @NotNull (message = "Password must not be null")
    private String password;
}