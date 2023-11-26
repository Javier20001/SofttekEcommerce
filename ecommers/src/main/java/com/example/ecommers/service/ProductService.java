package com.example.ecommers.service;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.repository.I_ProductRepository;
import com.example.ecommers.serviceInterface.I_CategoryService;
import com.example.ecommers.serviceInterface.I_ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements I_ProductService {

    @Autowired
    private I_ProductRepository productRepository;
    @Autowired
    private I_CategoryService categoryService;
    // public ProductoService(I_ProductoRepository productoRepository) {
    // this.productoRepository = productoRepository;
    // }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductEntity saveProduct(ProductEntity product) {
        ProductEntity productEntitys=saveCategory(product);
        product.setStatus(true);
        // Guardar el producto después de manejar la categoría
        return productRepository.save(productEntitys);
    }

    public ProductEntity updateProduct(Long id, ProductEntity newProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setProductName(newProduct.getProductName());
                    existingProduct.setProductStock(newProduct.getProductStock());
                    existingProduct.setProductImg(newProduct.getProductImg());
                    existingProduct.setProductPrice(newProduct.getProductPrice());
                    existingProduct=saveCategory(newProduct);
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
    private ProductEntity saveCategory(ProductEntity product){
        CategoryEntity categoryEntity = product.getTypeCategory();
        Optional<CategoryEntity> existingCategory = categoryService.getCategoryById(categoryEntity.getIdCategory());

        if (existingCategory.isPresent()) {
            // The category exists, assign it to the product
            product.setTypeCategory(existingCategory.get());
        } else {
            // The category does not exist, handle it accordingly
            categoryEntity.setStatus(true);
            categoryService.saveCategory(categoryEntity);
        }
        return product;
    }
}
