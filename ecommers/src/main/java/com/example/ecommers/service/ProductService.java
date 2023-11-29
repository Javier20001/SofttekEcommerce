package com.example.ecommers.service;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.repository.I_ProductRepository;
import com.example.ecommers.serviceInterface.I_CategoryService;
import com.example.ecommers.serviceInterface.I_ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
 * Service class implementing the business logic for managing products.
 *
 * This class is annotated with @Service to indicate that it is a service component in the Spring application context.
 * It implements the I_ProductService interface to provide the required functionality.
 *
 * @Service Indicates that this class is a Spring service component.
 * @see com.example.ecommers.serviceInterface.I_ProductService
 */
@Service
public class ProductService implements I_ProductService {

    /**
     * Repository interface for accessing and managing product entities in the database.
     */
    @Autowired
    private I_ProductRepository productRepository;

    /**
     * Service interface for handling category-related business logic.
     */
    @Autowired
    private I_CategoryService categoryService;

    /**
     * Retrieves a list of all products.
     *
     * @return List<ProductEntity> A list of all products.
     * @see com.example.ecommers.model.ProductEntity
     * @see com.example.ecommers.repository.I_ProductRepository#findAll()
     */
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its identifier.
     *
     * @param id The identifier of the product.
     * @return Optional<ProductEntity> An Optional containing the product, or empty if not found.
     * @see com.example.ecommers.model.ProductEntity
     * @see com.example.ecommers.repository.I_ProductRepository#findById(Object)
     */
    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Saves a new product.
     *
     * @param product The product entity to be saved.
     * @return ProductEntity The saved product.
     * @see com.example.ecommers.model.ProductEntity
     * @see com.example.ecommers.repository.I_ProductRepository#save(Object)
     */
    public ProductEntity saveProduct(ProductEntity product) {
        ProductEntity productEntity = saveCategory(product);
        product.setStatus(true);
        // Save the product after handling the category
        return productRepository.save(productEntity);
    }

    /**
     * Updates an existing product.
     *
     * @param id         The identifier of the product to be updated.
     * @param newProduct The updated product entity.
     * @return ProductEntity The updated product.
     * @throws RuntimeException if the product with the specified ID is not found.
     * @see com.example.ecommers.model.ProductEntity
     * @see com.example.ecommers.repository.I_ProductRepository#findById(Object)
     * @see com.example.ecommers.repository.I_ProductRepository#save(Object)
     */
    public ProductEntity updateProduct(Long id, ProductEntity newProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setProductName(newProduct.getProductName());
                    existingProduct.setProductStock(newProduct.getProductStock());
                    existingProduct.setProductImg(newProduct.getProductImg());
                    existingProduct.setProductPrice(newProduct.getProductPrice());
                    existingProduct = saveCategory(newProduct);
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Id product " + id + " not found"));
    }

    /**
     * Deletes a product by its identifier.
     *
     * @param id The identifier of the product to be deleted.
     * @throws RuntimeException if the product with the specified ID is not found.
     * @see com.example.ecommers.repository.I_ProductRepository#findById(Object)
     * @see com.example.ecommers.repository.I_ProductRepository#save(Object)
     */
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setStatus(false);
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Id product \" + id + \" not found"));
    }

    /**
     * Helper method to handle the category associated with a product.
     *
     * @param product The product entity to be processed.
     * @return ProductEntity The product entity with the associated category handled.
     * @see com.example.ecommers.model.ProductEntity
     * @see com.example.ecommers.serviceInterface.I_CategoryService#getCategoryById(Long)
     * @see com.example.ecommers.serviceInterface.I_CategoryService#saveCategory(CategoryEntity)
     */
    private ProductEntity saveCategory(ProductEntity product) {
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


    // Method to search products by name with partial match
    public List<ProductEntity> findByProductNameContainingIgnoreCase(String name) {

        if (name != null) {

            name = name.substring(0,3);
            return productRepository.findByProductNameContainingIgnoreCase(name);
        } else {
            return null;
        }
    }


    public List<ProductEntity> findByStatusTrue() {
        return productRepository.findByStatusTrue();
    }





}
