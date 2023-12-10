package com.example.ecommers.service;

import com.example.ecommers.model.ItemBillEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.repository.I_ItemBillRepository;
import com.example.ecommers.serviceInterface.I_ItemBillService;
import com.example.ecommers.serviceInterface.I_ProductService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemBillService implements I_ItemBillService {



    private final I_ItemBillRepository itemBillRepository;
    private final I_ProductService productService;

    public List<ItemBillEntity> getAllItemsBill() {
        try {
            return itemBillRepository.findAll();
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to retrieve all products. Reason: " + e.getMessage());
        }
    }

    public Optional<ItemBillEntity> getItemBillById(Long id) {
        try {
            return itemBillRepository.findById(id);
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to retrieve the product by ID. Reason: " + e.getMessage());
        }



    }


    public ItemBillEntity saveItem(@NotNull ItemBillEntity itemBillEntity) throws IllegalArgumentException {
        // Additional validation
        if (itemBillEntity.getId() != null) {
            throw new IllegalArgumentException("ItemBidEntity id must be null for save operation.");
        }

        // You can perform other custom validations here

        try {
            // Save the itemBidEntity using the repository
            return itemBillRepository.save(itemBillEntity);
        } catch (Exception e) {
            // Handle any persistence-related exceptions
            throw new RuntimeException("Error saving ItemBidEntity: " + e.getMessage(), e);
        }


}

    @Override
    public ProductEntity productMostSaled() {
        try {
            ProductEntity product=null;
            // Save the itemBidEntity using the repository
            if (productService.getProductById(itemBillRepository.findProductIdMostlySaled()).isPresent()){
                product=productService.getProductById(itemBillRepository.findProductIdMostlySaled()).get();
            }
            System.out.println(itemBillRepository.findProductIdMostlySaled().toString());
            System.out.println(product.toString());
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any persistence-related exceptions
            throw new RuntimeException("Error getting productMostSaled: " + e.getMessage(), e);
        }

    }

}