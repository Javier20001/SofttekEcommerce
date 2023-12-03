package com.example.ecommers.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private String userName;

    // Dirección de correo electrónico del usuario (único y no nulo)
    @Column(length = 40, unique = true, nullable = false)
    private String email;

    // Contraseña del usuario (no nula)
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
    private Boolean status;
}
