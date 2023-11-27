package com.example.ecommers;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.repository.I_CategoryRepository;
import com.example.ecommers.repository.I_ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductInitializer implements ApplicationRunner {
    private final I_ProductRepository i_productRepository;
    private final I_CategoryRepository i_categoryRepository;

    @Autowired
    public ProductInitializer(I_ProductRepository i_productRepository, I_CategoryRepository i_categoryRepository) {
        this.i_productRepository = i_productRepository;
        this.i_categoryRepository = i_categoryRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (i_productRepository.count() == 0) {
            CategoryEntity category = i_categoryRepository.findById(1L).orElseGet(() -> {
                CategoryEntity newCategory = new CategoryEntity();
                newCategory.setIdCategory(1L);
                newCategory.setCategory("mouse");
                newCategory.setStatus(true);
                return i_categoryRepository.save(newCategory);
            });
            for (int i = 0; i < 10; i++) {
                ProductEntity product = new ProductEntity();
                product.setProductName("Product " + i);
                product.setProductStock(4);
                product.setProductImg("https://s3-sa-east-1.amazonaws.com/saasargentina/oaPmQNJPQeMZynN9AOk5/imagen");
                product.setStatus(true);
                product.setProductPrice(500);
                product.setTypeCategory(category);
                i_productRepository.save(product);
            }
        }
    }
}