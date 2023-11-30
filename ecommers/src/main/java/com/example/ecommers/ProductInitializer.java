package com.example.ecommers;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.repository.I_CategoryRepository;
import com.example.ecommers.repository.I_ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductInitializer implements ApplicationRunner {
    private final I_ProductRepository i_productRepository;
    private final I_CategoryRepository i_categoryRepository;

    @Autowired
    public ProductInitializer(I_ProductRepository i_productRepository, I_CategoryRepository i_categoryRepository) {
        this.i_productRepository = i_productRepository;
        this.i_categoryRepository = i_categoryRepository;
    }
    //creation of products and categories
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (i_productRepository.count() == 0) {
            CategoryEntity category = i_categoryRepository.findById(1L).orElseGet(() -> {
                CategoryEntity newCategory = new CategoryEntity(1L,"mouse",true);
                return i_categoryRepository.save(newCategory);
            });
            ProductEntity product = new ProductEntity(1L,"Logitech rx pro",4,"https://s3-sa-east-1.amazonaws.com/saasargentina/oaPmQNJPQeMZynN9AOk5/imagen",new BigDecimal(500),true,category);
            i_productRepository.save(product);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category2 = i_categoryRepository.findById(2L).orElseGet(() -> {
                CategoryEntity newCategory2 = new CategoryEntity(2L,"keyboard",true);
                return i_categoryRepository.save(newCategory2);
            });
            ProductEntity product2 = new ProductEntity(2L,"Razer elite v1",14,"https://www.trustedreviews.com/wp-content/uploads/sites/54/2021/07/Best-gaming-keyboard-920x613.jpg",new BigDecimal(1500),true,category2);
            i_productRepository.save(product2);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category3 = i_categoryRepository.findById(3L).orElseGet(() -> {
                CategoryEntity newCategory3 = new CategoryEntity(3L,"RAM",true);
                return i_categoryRepository.save(newCategory3);
            });
            ProductEntity product3 = new ProductEntity(3L,"Kingston 8GB ddr4",10,"https://www.venex.com.ar/products_images/1599918773_8gb.jpg",new BigDecimal(3500),true,category3);
            i_productRepository.save(product3);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category4 = i_categoryRepository.findById(4L).orElseGet(() -> {
                CategoryEntity newCategory4 = new CategoryEntity(4L,"CPU",true);
                return i_categoryRepository.save(newCategory4);
            });
            ProductEntity product4 = new ProductEntity(4L,"Intel i9-9900k",3,"https://app.contabilium.com/files/explorer/7026/Productos-Servicios/concepto-6303558.jpg",new BigDecimal(10500),true,category4);
            i_productRepository.save(product4);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category5 = i_categoryRepository.findById(5L).orElseGet(() -> {
                CategoryEntity newCategory5 = new CategoryEntity(5L,"GPU",true);
                return i_categoryRepository.save(newCategory5);
            });
            ProductEntity product5 = new ProductEntity(5L,"Nvidia Radeon 5550xt",1,"https://www.igorslab.de/wp-content/uploads/2020/01/Intro.jpg", new BigDecimal(13500),true,category5);
            i_productRepository.save(product5);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category6 = i_categoryRepository.findById(6L).orElseGet(() -> {
                CategoryEntity newCategory6 = new CategoryEntity(6L,"monitor",true);
                return i_categoryRepository.save(newCategory6);
            });
            ProductEntity product6 = new ProductEntity(6L,"Asus ultra HD",2,"https://img.freepik.com/free-photo/view-computer-monitor-with-gradient-display_23-2150757379.jpg",new BigDecimal(12500),true,category6);
            i_productRepository.save(product6);
            //--------------------------------------------------------------------------------------------------------------------------
            CategoryEntity category7 = i_categoryRepository.findById(7L).orElseGet(() -> {
                CategoryEntity newCategory7 = new CategoryEntity(7L,"motherboard",true);
                return i_categoryRepository.save(newCategory7);
            });
            ProductEntity product7 = new ProductEntity(7L,"Msi a320m pro",2,"https://nutnullpc.com/images/thumbs/0001604_msi-a320m-pro-e-motherboard.png", new BigDecimal(5500),true,category7);
            i_productRepository.save(product7);
        }
    }
}