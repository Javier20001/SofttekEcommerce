package com.example.ecommers.controller;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.service.CategoryService;
import com.example.ecommers.serviceInterface.I_CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {

    private final I_CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    //Lista las categorias
    @GetMapping("/list")
    public ResponseEntity<List<CategoryEntity>> getAllCategorias() {
        List<CategoryEntity> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    
    //Muestra una categoria en particular
    /*@GetMapping("/Category/{id}")
    public ResponseEntity<CategoryEntity> getCategoriaById(@PathVariable Long id) {
        Optional<CategoryEntity> categoria = categoriaService.getCategoriasById(id);
        return categoria.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/
    
  
    //Save de categoria
    @PostMapping("/new")
    public ResponseEntity<CategoryEntity> saveCategory(@RequestBody CategoryEntity category) {
        CategoryEntity savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
    
    //Update de categoria
    @PutMapping("update/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable Long id, @RequestBody CategoryEntity newCategory) {
        CategoryEntity updatedCategory = categoryService.updateCategory(id, newCategory);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
    
    //Delete de categoria
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
