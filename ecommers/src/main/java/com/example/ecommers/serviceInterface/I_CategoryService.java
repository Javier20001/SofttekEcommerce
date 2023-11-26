package com.example.ecommers.serviceInterface;

import com.example.ecommers.model.CategoryEntity;
import java.util.List;
import java.util.Optional;


public interface I_CategoryService {
    
    public List<CategoryEntity> getAllCategory();
    public Optional<CategoryEntity> getCategoryById(Long id);
    public CategoryEntity saveCategory (CategoryEntity category);
    public CategoryEntity updateCategory(long id, CategoryEntity newCategory);
    public void deleteCategory(long id);
    
    
}
