package com.example.ecommers.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;
    @Column(length = 40, nullable = false)
    private String categoria;
    @Column(columnDefinition = "boolean default true")
    private boolean estado;
}
