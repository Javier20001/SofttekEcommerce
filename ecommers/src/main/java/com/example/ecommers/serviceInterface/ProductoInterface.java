package com.example.ecommers.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.example.ecommers.model.ProductoEntity;

public interface ProductoInterface {

    public List<ProductoEntity> getAllProductos();

    public Optional<ProductoEntity> getProductoById(Long id);

    public ProductoEntity updateProducto(Long id, ProductoEntity newProducto);

    public void deleteProducto(Long id);
}
