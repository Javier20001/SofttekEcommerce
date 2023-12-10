package com.example.ecommers.controller;


import com.example.ecommers.model.BillEntity;
import com.example.ecommers.serviceInterface.I_BillService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class for handling operations related to products.
 *
 * This class is responsible for managing CRUD (Create, Read, Update, Delete) operations
 * for bid and exposes corresponding API endpoints.
 *
 * @RestController Indicates that this class is a controller for handling HTTP requests.
 * @RequestMapping("/api/v1/bid") Base mapping for all endpoints in this controller.
 * @CrossOrigin(origins = "http://localhost:5173") Configures Cross-Origin Resource Sharing (CORS) for the controller.
 */

@RestController
@RequestMapping("/api/v1/bid")
@RequiredArgsConstructor
public class BillController {

    /**
     * Service interface for handling bid-related business logic.
     */
     private final I_BillService service;

    /**
     * Retrieves a list of all bids.
     *
     * @return String A JSON representation of the list of bids.
     * @GetMapping("/list") Mapping for the endpoint to retrieve the list of all bids.
     */
    @GetMapping("/list")
    public ResponseEntity<?> list(){
        try {
            List<BillEntity> list = service.getAllBill();
            Gson gson = new Gson();
            String json = gson.toJson(list);
            return new ResponseEntity<>(json, HttpStatus.OK);
        }catch (RuntimeException re){
            return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/count")
    public ResponseEntity<?> countBid(){
        try{
            return new ResponseEntity<>(service.countBill(), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>("Users not found", HttpStatus.BAD_REQUEST);
        }
    }
}
