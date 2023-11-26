package com.example.ecommers.service;

import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.repository.I_ProductRepository;
import com.example.ecommers.serviceInterface.ProductInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductInterface {

    @Autowired
    private I_ProductRepository productRepository;

    // public ProductoService(I_ProductoRepository productoRepository) {
    // this.productoRepository = productoRepository;
    // }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductEntity saveProduct(ProductEntity producto) {
        return productRepository.save(producto);
    }

    public ProductEntity updateProduct(Long id, ProductEntity newProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setProductName(newProduct.getProductName());
                    existingProduct.setProductStock(newProduct.getProductStock());
                    existingProduct.setProductImg(newProduct.getProductImg());
                    existingProduct.setProductPrice(newProduct.getProductPrice());
                    existingProduct.setTypeCategory(newProduct.getTypeCategory());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("El producto con ID " + id + " no se encontró"));
    }

    public void deleteProduct(Long id) {
        productRepository.findById(id).map(existingProduct -> {
            existingProduct.setStatus(false);
            return productRepository.save(existingProduct);
        })
                .orElseThrow(() -> new RuntimeException("El producto con ID " + id + " no se encontró"));
    }
}
