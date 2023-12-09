package com.example.ecommers.dto;

import com.example.ecommers.model.ItemBidEntity;
import com.example.ecommers.model.ItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class BidDTO {
    private Long id;
    private String province;
    private String locality;
    private String street;
    private String streetNumber;
    private String token;
    private Boolean isApartment;
    private String floorNumber;
    private String apartmentNumber;
    private List<ItemEntity> lstItem;
    private BigDecimal amount;
    private LocalDate selectedDate;
}
    /*province: "",
    locality: "",
    street: "",
    streetNumber: "",
    isApartment: false,
    apartmentNumber: "",
    floorNumber: "",
    paymentMPDTOFromFrontend,
    selectedDate: null,*/