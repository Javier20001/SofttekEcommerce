package com.example.ecommers.controller;


import com.example.ecommers.model.BidEntity;
import com.example.ecommers.repository.I_BidRepository;
import com.example.ecommers.serviceInterface.I_BidService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
@CrossOrigin(origins = "http://localhost:5173" )
@RequiredArgsConstructor
public class BidController {

    /**
     * Service interface for handling bid-related business logic.
     */
     private final I_BidService service;

    /**
     * Retrieves a list of all bids.
     *
     * @return String A JSON representation of the list of bids.
     * @GetMapping("/list") Mapping for the endpoint to retrieve the list of all bids.
     */
    @GetMapping("/list")
    public ResponseEntity<?> list(){
        try {
            List<BidEntity> list = service.getAllBid();
            Gson gson = new Gson();
            String json = gson.toJson(list);
            return new ResponseEntity<>(json, HttpStatus.OK);
        }catch (RuntimeException re){
            return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
