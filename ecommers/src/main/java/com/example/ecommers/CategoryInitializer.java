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
            category.setIdCategory(1L);
            category.setCategory("mouse");
            category.setStatus(true);
            i_categoryRepository.save(category);
            CategoryEntity category2 = new CategoryEntity();
            category2.setIdCategory(2L);
            category2.setCategory("keyboard");
            category2.setStatus(true);
            i_categoryRepository.save(category2);
            CategoryEntity category3 = new CategoryEntity();
            category3.setIdCategory(3L);
            category3.setCategory("monitor");
            category3.setStatus(true);
            i_categoryRepository.save(category3);
            CategoryEntity category4 = new CategoryEntity();
            category4.setIdCategory(4L);
            category4.setCategory("ram");
            category4.setStatus(true);
            i_categoryRepository.save(category4);
            CategoryEntity category5 = new CategoryEntity();
            category5.setIdCategory(5L);
            category5.setCategory("CPU");
            category5.setStatus(true);
            i_categoryRepository.save(category5);
            CategoryEntity category6 = new CategoryEntity();
            category6.setIdCategory(6L);
            category6.setCategory("GPU");
            category6.setStatus(true);
            i_categoryRepository.save(category6);
            CategoryEntity category7 = new CategoryEntity();
            category7.setIdCategory(7L);
            category7.setCategory("motherboard");
            category7.setStatus(true);
            i_categoryRepository.save(category7);
        }
    }
}
