package com.example.ecommers.controller;

import com.example.ecommers.model.BillEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.serviceInterface.I_ItemBillService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/itemBill")
@RequiredArgsConstructor
public class ItemBillController {

    private final I_ItemBillService service;

    @GetMapping("/mostSales")
    public ResponseEntity<?> listByBillId() {
        try {
            return new ResponseEntity<>(service.productMostSaled(), HttpStatus.OK);
        } catch (RuntimeException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
