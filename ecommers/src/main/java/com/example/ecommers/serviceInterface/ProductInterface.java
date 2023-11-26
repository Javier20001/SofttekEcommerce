package com.example.ecommers.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.example.ecommers.model.ProductEntity;

public interface ProductInterface {

    public List<ProductEntity> getAllProducts();

    public Optional<ProductEntity> getProductById(Long id);
    
    public ProductEntity saveProduct(ProductEntity product);

    public ProductEntity updateProduct(Long id, ProductEntity newProduct);

    public void deleteProduct(Long id);
}
