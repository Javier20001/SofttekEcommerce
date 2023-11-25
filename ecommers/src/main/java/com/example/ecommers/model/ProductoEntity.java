package com.example.ecommers.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    
    //nombre del producto
    @Column(length = 100, nullable = false)
    private String productoNombre;

    // Cantidad disponible en el inventario
    @Column(nullable = false)
    private int productoCantidad;

    // Imagen asociada al producto
    @Column(length = 200)
    private String productoImagen;

    // Precio del producto
    @Column(nullable = false)
    private double productoPrecio;

    // Categoría del producto (relación Many-to-One con CategoryEntity)
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoryEntity tipoCategoria;
    
}

    
