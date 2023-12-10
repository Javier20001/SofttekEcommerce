package com.example.ecommers.service;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.repository.I_CategoryRepository;
import com.example.ecommers.serviceInterface.I_CategoryService;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class implementing the business logic for managing product categories.
 *
 * This class is annotated with @Service to indicate that it is a service component in the Spring application context.
 * It implements the I_CategoryService interface to provide the required functionality.
 *
 * @Service Indicates that this class is a Spring service component.
 * @see com.example.ecommers.serviceInterface.I_CategoryService
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService implements I_CategoryService {

    /**
     * Repository interface for accessing and managing category entities in the database.
     */

    private final I_CategoryRepository categoryRepository;

    /**
     * Retrieves a list of all product categories.
     *
     * @return List<CategoryEntity> A list of all product categories.
     * @see com.example.ecommers.model.CategoryEntity
     * @see com.example.ecommers.repository.I_CategoryRepository#findAll()
     */
    @Override
    public List<CategoryEntity> getAllCategory() {
        try {
            return categoryRepository.filterByStatus();
        }catch (Exception e){
            throw new RuntimeException("Error searching all categories");
        }
    }

    /**
     * Retrieves a product category by its identifier.
     *
     * @param id The identifier of the category.
     * @return Optional<CategoryEntity> An Optional containing the product category, or empty if not found.
     * @see com.example.ecommers.model.CategoryEntity
     * @see com.example.ecommers.repository.I_CategoryRepository#findById(Object)
     */
    @Override
    public Optional<CategoryEntity> getCategoryById(Long id) {
        try {
            if (id != null) {
                return categoryRepository.findById(id);
            } else {
                return Optional.empty();
            }
        }catch(Exception e){
            throw new RuntimeException("Error searching for category, please retry");
        }
    }

    /**
     * Saves a new product category.
     *
     * @param category The product category entity to be saved.
     * @return CategoryEntity The saved product category.
     * @see com.example.ecommers.model.CategoryEntity
     * @see com.example.ecommers.repository.I_CategoryRepository#save(Object)
     */
    @Override
    public CategoryEntity saveCategory(CategoryEntity category) {
        try {
            if (categoryRepository.existsByCategory(category.getCategory())) {
                throw new EntityExistsException("Already exist a category with the name: " + category.getCategory());
            }
            category.setStatus(true);
            return categoryRepository.save(category);
        }catch (Exception e){
            throw new RuntimeException("Could not save category");
        }
    }


    /**
     * Updates an existing product category.
     *
     * @param id         The identifier of the category to be updated.
     * @param newCategory The updated category entity.
     * @return CategoryEntity The updated product category.
     * @throws RuntimeException if the category with the specified ID is not found.
     * @see com.example.ecommers.model.CategoryEntity
     * @see com.example.ecommers.repository.I_CategoryRepository#findById(Object)
     * @see com.example.ecommers.repository.I_CategoryRepository#save(Object)
     */
    @Override
    public CategoryEntity updateCategory(long id, CategoryEntity newCategory) {
        try {
            return categoryRepository.findById(id).map(existingCategory -> {
                        existingCategory.setCategory(newCategory.getCategory());

                        return categoryRepository.save(existingCategory);
                    })
                    .orElseThrow(() -> new RuntimeException("Id category " + id + " not found"));
        }catch (Exception e){
            throw new RuntimeException("Could not update category");
        }
    }

    /**
     * Deletes a product category by its identifier.
     *
     * @param id The identifier of the category to be deleted.
     * @throws RuntimeException if the category with the specified ID is not found.
     * @see com.example.ecommers.repository.I_CategoryRepository#findById(Object)
     * @see com.example.ecommers.repository.I_CategoryRepository#save(Object)
     */
    @Override
    public void deleteCategory(long id) {
        try {
            categoryRepository.findById(id).map(existingCategory -> {
                        existingCategory.setStatus(false);
                        return categoryRepository.save(existingCategory);
                    })
                    .orElseThrow(() -> new RuntimeException("Id category " + id + " not found"));
        }catch (Exception e){
            throw new RuntimeException("Could not delete category");
        }
    }
}

