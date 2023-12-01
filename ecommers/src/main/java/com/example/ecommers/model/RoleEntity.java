package com.example.ecommers.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;



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

    // Relación Many-to-Many con la entidad User, mapeada por el atributo 'roles' en User
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}