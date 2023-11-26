package com.example.ecommers.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    // nombre del producto
    @Column(length = 100, nullable = false)
    private String productName;

    // Cantidad disponible en el inventario
    @Column(nullable = false)
    private int productStock;

    // Imagen asociada al producto
    @Column(length = 200)
    private String productImg;

    // Precio del producto
    @Column(nullable = false)
    private double productPrice;

    @Column(columnDefinition = "boolean default true")
    private Boolean status;

    // Categoría del producto (relación Many-to-One con CategoryEntity)
    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private CategoryEntity typeCategory;

}
