package com.example.ecommers.service;


import com.example.ecommers.model.BidEntity;
import com.example.ecommers.repository.I_BidRepository;
import com.example.ecommers.serviceInterface.I_BidService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidService implements I_BidService {


    @Autowired
    private I_BidRepository bidRepository;

    @Override
    public List<BidEntity> getAllBid() {
        try{
            return bidRepository.findAll();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("failure to bring the invoice. Reason: " + e.getMessage());
        }
    }

    @Override
    public Optional<BidEntity> getBidById(Long id) {
        try {
            return bidRepository.findById(id);
        }catch( Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failure to bring the invoice by id. Reason: " + e.getMessage());
        }

    }

    @Override
    public BidEntity saveBid(@NotNull BidEntity bid) throws IllegalArgumentException {
        if(bid.getId() != null){
            throw new IllegalArgumentException("bid id must be null for save operation.");
        }
        try{
            return bidRepository.save(bid);
        }catch(Exception e){
            throw new RuntimeException("Error saving bid: " + e.getMessage(), e);
        }

    }
}
