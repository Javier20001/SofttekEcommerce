package com.example.ecommers.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(length = 100, nullable = false)
    private String productoNombre;

    @Column(nullable = false)
    private int productoCantidad;

    @Column(length = 200)
    private String productoImagen;

    @Column(nullable = false)
    private double productoPrecio;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoryEntity tipoCategoria;
    
}

    
