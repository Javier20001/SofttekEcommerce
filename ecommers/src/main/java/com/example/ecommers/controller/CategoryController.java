package com.example.ecommers.controller;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.service.CategoriaService;
import com.example.ecommers.serviceInterface.InterCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class CategoryController {

    private final InterCategoriaService categoriaService;

    @Autowired
    public CategoryController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    
    //Lista las categorias
    @GetMapping("/Category")
    public ResponseEntity<List<CategoryEntity>> getAllCategorias() {
        List<CategoryEntity> categorias = categoriaService.getAllCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
    
    //Muestra una categoria en particular
    /*@GetMapping("/Category/{id}")
    public ResponseEntity<CategoryEntity> getCategoriaById(@PathVariable Long id) {
        Optional<CategoryEntity> categoria = categoriaService.getCategoriasById(id);
        return categoria.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/
    
  
    //Save de categoria
    @PostMapping
    public ResponseEntity<CategoryEntity> saveCategoria(@RequestBody CategoryEntity categoria) {
        CategoryEntity savedCategoria = categoriaService.saveCategoria(categoria);
        return new ResponseEntity<>(savedCategoria, HttpStatus.CREATED);
    }
    
    //Update de categoria
    @PutMapping("/Category/Update/{id}")
    public ResponseEntity<CategoryEntity> updateCategoria(@PathVariable Long id, @RequestBody CategoryEntity newCategoria) {
        CategoryEntity updatedCategoria = categoriaService.updateCategoria(id, newCategoria);
        return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
    }
    
    //Delete de categoria
    @DeleteMapping("/Category/Delete/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
