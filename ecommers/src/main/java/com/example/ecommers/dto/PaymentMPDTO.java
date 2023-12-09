package com.example.ecommers.dto;

import com.example.ecommers.model.ItemBidEntity;
import com.example.ecommers.model.ItemEntity;
import lombok.Data;

import java.util.List;

@Data
public class PaymentMPDTO {
    private List<ItemEntity> lstItem;
}
