package com.example.ecommers.dto;

import com.example.ecommers.model.ItemEntity;

import java.math.BigDecimal;
import java.util.List;

public class BidDTO {
    private Long id;
    private Long idUser;
    private List<ItemEntity> lstItem;
    private String dir;
    private BigDecimal amount;
}
