package com.example.ecommers.service;


import com.example.ecommers.dto.BidDTO;
import com.example.ecommers.model.*;
import com.example.ecommers.repository.I_BidRepository;
import com.example.ecommers.repository.I_UserRepository;
import com.example.ecommers.security.JwtUtil;
import com.example.ecommers.serviceInterface.I_BidService;
import com.example.ecommers.serviceInterface.I_DirService;
import com.example.ecommers.serviceInterface.I_ItemBidService;
import com.example.ecommers.serviceInterface.I_ProductService;
import com.mercadopago.client.preference.PreferenceItemRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class BidService implements I_BidService {


    @Autowired
    private I_BidRepository bidRepository;


    private final I_UserRepository userRepository;
    private final I_ItemBidService iItemBidService;
    private final I_ProductService productService;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;
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

    @Transactional
    @Override
    public BidEntity saveBid(@NotNull BidDTO bidDTO) throws IllegalArgumentException {
        if(bidDTO.getId() != null){
            throw new IllegalArgumentException("bid id must be null for save operation.");
        }
        try{
            UserEntity userEntity=null;
            if (userRepository.findByEmail(jwtUtil.getUserNameToken(bidDTO.getToken())).isPresent()){
                userEntity=userRepository.findByEmail(jwtUtil.getUserNameToken(bidDTO.getToken())).get();
            }
            DirEntity dir=modelMapper.map(bidDTO,DirEntity.class);

            BidEntity bid=bidRepository.save(BidEntity.builder()
                            .user(userEntity)
                            .dir(dir)
                    .build());

            for (ItemEntity item : bidDTO.getLstItem()) {
                ItemBidEntity itemRequest = ItemBidEntity.builder()
                        .idProduct(item.getProduct().getIdProduct())
                        .quantitySelected(item.getQuantitySelected())
                        .bid(bid)
                        .build();
                iItemBidService.saveItem(itemRequest);

                if (productService.getProductById(itemRequest.getIdProduct()).isPresent()){
                    ProductEntity productEntity=productService.getProductById(itemRequest.getIdProduct()).get();
                    System.out.println(productEntity.getProductStock());
                    productEntity.setProductStock(productEntity.getProductStock()-itemRequest.getQuantitySelected());
                    System.out.println(productEntity.getProductStock());
                }
            }
            return bid;
        }catch(Exception e){
            throw new RuntimeException("Error saving bid: " + e.getMessage(), e);
        }
    }
}
