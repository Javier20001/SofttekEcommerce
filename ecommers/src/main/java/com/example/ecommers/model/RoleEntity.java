package com.example.ecommers.model;

import lombok.*;
import jakarta.persistence.*;


/**
 * Entidad que representa los roles de usuarios en el sistema.
 */
@AllArgsConstructor 
@NoArgsConstructor
@Entity
@Data
public class RoleEntity {
    
    // Identificador único del rol
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del rol, utilizado como un Enum para definir roles predefinidos
    @Enumerated(EnumType.STRING)
    private RolesName name;

}