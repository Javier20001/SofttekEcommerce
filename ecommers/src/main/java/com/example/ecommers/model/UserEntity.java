package com.example.ecommers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor 
@NoArgsConstructor
@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nickname del usuario (único y no nulo)
    @Column(length = 20, unique = true, nullable = false)
    @NotBlank(message = "User is required")
    @NotNull
    private String userName;

    // Dirección de correo electrónico del usuario (único y no nulo)
    @Column(length = 40, unique = true, nullable = false)
    @Email(message = "Must be a valid email address")
    @NotBlank(message = "Email is required")
    @NotNull
    private String email;

    // Contraseña del usuario (no nula)
    @NotBlank(message = "Password is required")
    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(length = 60, nullable = false)
    private String password;

    //token de reseteo
    @Column
    private String resetToken;

    //duracion del token
    @Column
    private LocalDateTime expirationDate;

    // Roles asociados al usuario (relación Many-to-Many con Rol)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> roles;
    
    @Column(columnDefinition = "boolean default true")
    @NotNull
    private Boolean status;
}
