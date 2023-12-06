package com.example.ecommers.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemEntity {
    private Long idItem;
    private int cantidadSeleccioanda;
    private ProductEntity product;
    private BigDecimal totalForProduct;
}
