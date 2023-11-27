package com.example.ecommers.serviceInterface;

import com.example.ecommers.model.CategoryEntity;
import java.util.List;
import java.util.Optional;


/**
 * Service interface defining the contract for managing product categories.
 * Implementing classes should provide the business logic for CRUD operations on product categories.
 */
public interface I_CategoryService {

    /**
     * Retrieves a list of all product categories.
     *
     * @return List<CategoryEntity> A list of all product categories.
     * @see com.example.ecommers.model.CategoryEntity
     */
    List<CategoryEntity> getAllCategory();

    /**
     * Retrieves a product category by its identifier.
     *
     * @param id The identifier of the category.
     * @return Optional<CategoryEntity> An Optional containing the product category, or empty if not found.
     * @see com.example.ecommers.model.CategoryEntity
     */
    Optional<CategoryEntity> getCategoryById(Long id);

    /**
     * Saves a new product category.
     *
     * @param category The product category entity to be saved.
     * @return CategoryEntity The saved product category.
     * @see com.example.ecommers.model.CategoryEntity
     */
    CategoryEntity saveCategory(CategoryEntity category);

    /**
     * Updates an existing product category.
     *
     * @param id         The identifier of the category to be updated.
     * @param newCategory The updated category entity.
     * @return CategoryEntity The updated product category.
     * @throws RuntimeException if the category with the specified ID is not found.
     * @see com.example.ecommers.model.CategoryEntity
     */
    CategoryEntity updateCategory(long id, CategoryEntity newCategory);

    /**
     * Deletes a product category by its identifier.
     *
     * @param id The identifier of the category to be deleted.
     * @throws RuntimeException if the category with the specified ID is not found.
     */
    void deleteCategory(long id);
}

