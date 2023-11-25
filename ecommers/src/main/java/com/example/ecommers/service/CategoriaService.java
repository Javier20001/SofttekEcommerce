package com.example.ecommers.service;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.repository.I_CategoryRepository;
import com.example.ecommers.serviceInterface.InterCategoriaService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements InterCategoriaService {
    
    @Autowired
    private I_CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getAllCategorias() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryEntity> getCategoriasById(Long id) {
       return categoryRepository.findById(id);
    }

    @Override
    public CategoryEntity saveCategoria(CategoryEntity categoria) {
        return categoryRepository.save(categoria);
    }

    @Override
    public CategoryEntity updateCategoria(long id, CategoryEntity newCategoria) {
       return categoryRepository.findById(id).map(existingCategoria -> {
                    existingCategoria.setCategoria(newCategoria.getCategoria());
                    
                    return categoryRepository.save(existingCategoria);
                })
                .orElseThrow(() -> new RuntimeException("La categoria con ID " + id + " no se encontró"));
    }

    @Override
    public void deleteCategoria(long id) {
        categoryRepository.findById(id).map(existingCategoria ->{
            existingCategoria.setEstado(false);
            return categoryRepository.save(existingCategoria);
        })
                
                .orElseThrow(() -> new RuntimeException("La categoria con ID " + id + " no se encontró"));
    }

   
   
    
}
