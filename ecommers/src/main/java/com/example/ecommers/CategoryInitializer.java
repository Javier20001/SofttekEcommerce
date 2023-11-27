package com.example.ecommers;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.repository.I_CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryInitializer implements ApplicationRunner {
    private final I_CategoryRepository i_categoryRepository;

    @Autowired
    public CategoryInitializer(I_CategoryRepository i_categoryRepository) {
        this.i_categoryRepository = i_categoryRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (i_categoryRepository.count() == 0) {
                CategoryEntity category = new CategoryEntity();
                category.setIdCategoria(1L);
                category.setCategoria("mouse");
                category.setEstado(true);
                i_categoryRepository.save(category);
                CategoryEntity category2 = new CategoryEntity();
                category2.setIdCategoria(2L);
                category2.setCategoria("keyboard");
                category2.setEstado(true);
                i_categoryRepository.save(category2);
                CategoryEntity category3 = new CategoryEntity();
                category3.setIdCategoria(3L);
                category3.setCategoria("monitor");
                category3.setEstado(true);
                i_categoryRepository.save(category3);
        }
    }
}
