package com.example.ecommers.serviceInterface;

import com.example.ecommers.model.CategoryEntity;
import java.util.List;
import java.util.Optional;


public interface InterCategoriaService {
    
    public List<CategoryEntity> getAllCategorias();
    public Optional<CategoryEntity> getCategoriasById(Long id);
    public CategoryEntity saveCategoria (CategoryEntity categoria);
    public CategoryEntity updateCategoria(long id, CategoryEntity newCategoria);
    public void deleteCategoria(long id);
    
    
}
