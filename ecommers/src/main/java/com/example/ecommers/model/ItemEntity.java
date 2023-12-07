package com.example.ecommers.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemEntity {
    private Long idItem;
    private int quantitySelected;
    private ProductEntity product;
    private BigDecimal totalForProduct;
}
