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
    public ResponseEntity<?> getAllCategories() {
        try {
            return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Error while searching for categories", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Saves a new category.
     *
     * @param category The category entity to be saved.
     * @return ResponseEntity<CategoryEntity> A response entity containing the saved category with HTTP status.
     * @PostMapping("/new") Mapping for the endpoint to save a new category.
     */
    @PostMapping("/new")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryEntity category) {
        try {
            return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Error while saving category", HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryEntity newCategory) {
        try {
            return new ResponseEntity<>(categoryService.updateCategory(id, newCategory), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error while updating category", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a category by its identifier.
     *
     * @param id The identifier of the category to be deleted.
     * @return ResponseEntity<Void> A response entity with HTTP status indicating the success of the delete operation.
     * @DeleteMapping("delete/{id}") Mapping for the endpoint to delete a category by its identifier.
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>("Error while deleting category", HttpStatus.BAD_REQUEST);
        }
    }
}
