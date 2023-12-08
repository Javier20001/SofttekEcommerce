package com.example.ecommers.controller;


import com.example.ecommers.model.BidEntity;
import com.example.ecommers.repository.I_BidRepository;
import com.example.ecommers.serviceInterface.I_BidService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@NoArgsConstructor
@AllArgsConstructor
public class BidController {

    /**
     * Service interface for handling bid-related business logic.
     */
    @Autowired
    private I_BidService service;

    /**
     * Retrieves a list of all bids.
     *
     * @return String A JSON representation of the list of bids.
     * @GetMapping("/list") Mapping for the endpoint to retrieve the list of all bids.
     */
    @GetMapping("/list")
    public String list(){
        List<BidEntity> list = service.getAllBid();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        System.out.println();
        return json;

    }
}
