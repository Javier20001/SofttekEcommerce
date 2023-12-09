package com.example.ecommers.service;

import com.example.ecommers.exception.ProductAlrdyExist;
import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.repository.I_ProductRepository;
import com.example.ecommers.serviceInterface.I_CategoryService;
import com.example.ecommers.serviceInterface.I_ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
@RequiredArgsConstructor
@Transactional
public class ProductService implements I_ProductService {

    /**
     * Repository interface for accessing and managing product entities in the database.
     */

    private final I_ProductRepository productRepository;

    /**
     * Service interface for handling category-related business logic.
     */

    private final I_CategoryService categoryService;

    /**
     * Retrieves a list of all products.
     *
     * @return List<ProductEntity> A list of all products.
     * @see com.example.ecommers.model.ProductEntity
     * @see com.example.ecommers.repository.I_ProductRepository#findAll()
     */
    public List<ProductEntity> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to retrieve all products. Reason: " + e.getMessage());
        }
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
        try {
            return productRepository.findById(id);
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to retrieve the product by ID. Reason: " + e.getMessage());
        }
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
        try {
            // Save the category first
            ProductEntity productEntity = saveCategory(product);

            // Update the product status
            product.setStatus(true);
            if(productRepository.findByProductNameIgnoreCaseAndStatus(product.getProductName(),productEntity.getStatus()).isPresent()){
                throw new ProductAlrdyExist("Este producto ya existe.");
            }else {
                // Save the product after handling the category
                return productRepository.save(productEntity);
            }
        } catch (ProductAlrdyExist ex) {    
            // Catch and rethrow the specific exception for GlobalExceptionHandler
            throw ex;
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to save the product. Reason: " + e.getMessage());
        }
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
        try {
            Optional<ProductEntity> optionalExistingProduct = productRepository.findById(id);
            return optionalExistingProduct.map(existingProduct -> {
                existingProduct.setProductName(newProduct.getProductName());
                existingProduct.setProductStock(newProduct.getProductStock());
                existingProduct.setProductImg(newProduct.getProductImg());
                existingProduct.setProductPrice(newProduct.getProductPrice());
                existingProduct.setDescription(newProduct.getDescription());
                existingProduct = saveCategory(newProduct);
                return productRepository.save(existingProduct);
            }).orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found"));
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to update product. Reason: " + e.getMessage());
        }
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
        try {
            productRepository.findById(id)
                    .map(existingProduct -> {
                        existingProduct.setStatus(false);
                        return productRepository.save(existingProduct);
                    })
                    .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found"));
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to delete product. Reason: " + e.getMessage());
        }
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
        try {
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
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to handle category for product. Reason: " + e.getMessage());
        }
    }


    // Method to search products by name with partial match
    public List<ProductEntity> findByProductNameContainingIgnoreCase(String name) {
        try {
            if (name != null) {
                // Modify the name (e.g., take the first three characters)
                name = name.substring(0, Math.min(name.length(), 3));

                // Perform the repository query
                return productRepository.findByProductNameContainingIgnoreCase(name);
            } else {
                // You might want to throw an IllegalArgumentException or handle it differently
                throw new IllegalArgumentException("Name cannot be null");
            }
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to perform product search. Reason: " + e.getMessage());
        }
    }



    public List<ProductEntity> findByStatusTrue() {
        try {
            return productRepository.findByStatusTrue();
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to retrieve products with status true. Reason: " + e.getMessage());
        }
    }






}
