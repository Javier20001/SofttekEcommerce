package com.example.ecommers.service;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.repository.I_CategoryRepository;
import com.example.ecommers.serviceInterface.I_CategoryService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements I_CategoryService {
    
    @Autowired
    private I_CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryEntity> getCategoryById(Long id) {
        if (id != null) {
            return categoryRepository.findById(id);
        } else {
            // Handle the case where the ID is null (perhaps throw an exception or return an empty Optional)
            return Optional.empty();
        }
    }

    @Override
    public CategoryEntity saveCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryEntity updateCategory(long id, CategoryEntity newCategory) {
       return categoryRepository.findById(id).map(existingCategoria -> {
                    existingCategoria.setCategory(newCategory.getCategory());
                    
                    return categoryRepository.save(existingCategoria);
                })
                .orElseThrow(() -> new RuntimeException("La categoria con ID " + id + " no se encontró"));
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.findById(id).map(existingCategoria ->{
            existingCategoria.setStatus(false);
            return categoryRepository.save(existingCategoria);
        })
                
                .orElseThrow(() -> new RuntimeException("La categoria con ID " + id + " no se encontró"));
    }

   
   
    
}
