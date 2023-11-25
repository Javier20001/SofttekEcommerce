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

    // Constructores, getters y setters

    public Producto() {
    }

    public Producto(Long idProducto, String productoNombre, int productoCantidad, String productoImagen, double productoPrecio, CategoryEntity tipoCategoria) {
        this.idProducto = idProducto;
        this.productoNombre = productoNombre;
        this.productoCantidad = productoCantidad;
        this.productoImagen = productoImagen;
        this.productoPrecio = productoPrecio;
        this.tipoCategoria = tipoCategoria;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public int getProductoCantidad() {
        return productoCantidad;
    }

    public void setProductoCantidad(int productoCantidad) {
        this.productoCantidad = productoCantidad;
    }

    public String getProductoImagen() {
        return productoImagen;
    }

    public void setProductoImagen(String productoImagen) {
        this.productoImagen = productoImagen;
    }

    public double getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(double productoPrecio) {
        this.productoPrecio = productoPrecio;
    }

    public CategoryEntity getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(CategoryEntity tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }
    
    
}

    
