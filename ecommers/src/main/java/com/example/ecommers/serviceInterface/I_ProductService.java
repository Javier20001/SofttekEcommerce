package com.example.ecommers.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.example.ecommers.model.ProductEntity;

/**
 * Service interface defining the contract for managing products.
 * Implementing classes should provide the business logic for CRUD operations on products.
 */
public interface I_ProductService {

    /**
     * Retrieves a list of all products.
     *
     * @return List<ProductEntity> A list of all products.
     * @see com.example.ecommers.model.ProductEntity
     */
    List<ProductEntity> getAllProducts();

    /**
     * Retrieves a product by its identifier.
     *
     * @param id The identifier of the product.
     * @return Optional<ProductEntity> An Optional containing the product, or empty if not found.
     * @see com.example.ecommers.model.ProductEntity
     */
    Optional<ProductEntity> getProductById(Long id);

    /**
     * Saves a new product.
     *
     * @param product The product entity to be saved.
     * @return ProductEntity The saved product.
     * @see com.example.ecommers.model.ProductEntity
     */
    ProductEntity saveProduct(ProductEntity product);

    /**
     * Updates an existing product.
     *
     * @param id        The identifier of the product to be updated.
     * @param newProduct The updated product entity.
     * @return ProductEntity The updated product.
     * @throws RuntimeException if the product with the specified ID is not found.
     * @see com.example.ecommers.model.ProductEntity
     */
    ProductEntity updateProduct(Long id, ProductEntity newProduct);

    /**
     * Deletes a product by its identifier.
     *
     * @param id The identifier of the product to be deleted.
     * @throws RuntimeException if the product with the specified ID is not found.
     */
    void deleteProduct(Long id);
}

