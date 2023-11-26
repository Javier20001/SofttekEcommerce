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
    private Long idCategory;
    @Column(length = 40, nullable = false)
    private String category;
    @Column(columnDefinition = "boolean default true",nullable = false)
    private Boolean status;
}
