package com.example.ecommers.service;

import com.example.ecommers.model.ItemBidEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.repository.I_ItemBidRepository;
import com.example.ecommers.serviceInterface.I_ItemBidService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemBidService implements I_ItemBidService {


    @Autowired
    private I_ItemBidRepository itemBidRepository;

    public List<ItemBidEntity> getAllItemsBid() {
        try {
            return itemBidRepository.findAll();
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to retrieve all products. Reason: " + e.getMessage());
        }
    }

    public Optional<ItemBidEntity> getItemBidById(Long id) {
        try {
            return itemBidRepository.findById(id);
        } catch (Exception e) {
            // Handle the exception appropriately, you can log it or throw a custom exception
            // For demonstration purposes, let's print the stack trace
            e.printStackTrace();

            // You might want to throw a custom exception or return a specific error response
            // based on your application's requirements
            throw new RuntimeException("Failed to retrieve the product by ID. Reason: " + e.getMessage());
        }



    }


    public ItemBidEntity saveItem(@NotNull ItemBidEntity itemBidEntity) throws IllegalArgumentException {
        // Additional validation
        if (itemBidEntity.getId() != null) {
            throw new IllegalArgumentException("ItemBidEntity id must be null for save operation.");
        }

        // You can perform other custom validations here

        try {
            // Save the itemBidEntity using the repository
            return itemBidRepository.save(itemBidEntity);
        } catch (Exception e) {
            // Handle any persistence-related exceptions
            throw new RuntimeException("Error saving ItemBidEntity: " + e.getMessage(), e);
        }


}

    }