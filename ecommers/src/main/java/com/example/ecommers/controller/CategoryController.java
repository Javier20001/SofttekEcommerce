package com.example.ecommers.controller;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.service.CategoryService;
import com.example.ecommers.serviceInterface.I_CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class for handling operations related to product categories.

 * This class is responsible for managing CRUD (Create, Read, Update, Delete) operations
 * for product categories and exposes corresponding API endpoints.
 *
 * @RestController Indicates that this class is a controller for handling HTTP requests.
 * @RequestMapping("/api/v1/category") Base mapping for all endpoints in this controller.
 * @CrossOrigin(origins = "<a href="http://localhost:5173">...</a>") Configures Cross-Origin Resource Sharing (CORS) for the controller.
 */
@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class CategoryController {

    /**
     * Service interface for handling category-related business logic.
     */
     private final I_CategoryService categoryService;

    /**
     * Constructor for CategoryController.
     *
     * @param categoryService The service responsible for category-related operations.
     *  Annotation for automatic dependency injection.
     */

    /**
     * Retrieves a list of all categories.
     *
     * @return ResponseEntity<List<CategoryEntity>> A response entity containing the list of categories with HTTP status.
     * @GetMapping("/list") Mapping for the endpoint to retrieve the list of all categories.
     */
    @GetMapping("/list")
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        List<CategoryEntity> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * Saves a new category.
     *
     * @param category The category entity to be saved.
     * @return ResponseEntity<CategoryEntity> A response entity containing the saved category with HTTP status.
     * @PostMapping("/new") Mapping for the endpoint to save a new category.
     */
    @PostMapping("/new")
    public ResponseEntity<CategoryEntity> saveCategory(@RequestBody CategoryEntity category) {
        CategoryEntity savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    /**
     * Updates an existing category.
     *
     * @param id         The identifier of the category to be updated.
     * @param newCategory The updated category entity.
     * @return ResponseEntity<CategoryEntity> A response entity containing the updated category with HTTP status.
     * @PutMapping("update/{id}") Mapping for the endpoint to update an existing category.
     */
    @PutMapping("update/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable Long id, @RequestBody CategoryEntity newCategory) {
        CategoryEntity updatedCategory = categoryService.updateCategory(id, newCategory);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    /**
     * Deletes a category by its identifier.
     *
     * @param id The identifier of the category to be deleted.
     * @return ResponseEntity<Void> A response entity with HTTP status indicating the success of the delete operation.
     * @DeleteMapping("delete/{id}") Mapping for the endpoint to delete a category by its identifier.
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
