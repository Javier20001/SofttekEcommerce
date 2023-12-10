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
@Service
@Transactional
@RequiredArgsConstructor
public class BidService implements I_BidService {



    private final I_BidRepository bidRepository;


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
            throw new RuntimeException("failure to bring the invoice. Reason: " + e.getMessage());
        }
    }

    @Override
    public Optional<BidEntity> getBidById(Long id) {
        try {
            return bidRepository.findById(id);
        }catch( RuntimeException e) {
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
            UserEntity userEntity=createUser(bidDTO);
            DirEntity dir=modelMapper.map(bidDTO,DirEntity.class);

            BidEntity bid=bidRepository.save(BidEntity.builder()
                            .user(userEntity)
                            .dir(dir)
                    .build());
            saveItem(bidDTO,bid);
            return bid;
        }catch(Exception e){
            throw new RuntimeException("Error saving bid: " + e.getMessage(), e);
        }
    }
    private UserEntity createUser(BidDTO bidDTO){
        UserEntity userEntity=null;
        if (userRepository.findByEmail(jwtUtil.getUserNameToken(bidDTO.getToken())).isPresent()){
            userEntity=userRepository.findByEmail(jwtUtil.getUserNameToken(bidDTO.getToken())).get();
        }
        return userEntity;
    }
    private void saveItem (BidDTO bidDTO,BidEntity bid){
        for (ItemEntity item : bidDTO.getLstItem()) {
            ItemBidEntity itemRequest = ItemBidEntity.builder()
                    .idProduct(item.getProduct().getIdProduct())
                    .quantitySelected(item.getQuantitySelected())
                    .bid(bid)
                    .build();
            iItemBidService.saveItem(itemRequest);
            descontarStock(itemRequest);
        }
    }

    private void descontarStock(ItemBidEntity itemRequest){
        if (productService.getProductById(itemRequest.getIdProduct()).isPresent()){
            ProductEntity productEntity=productService.getProductById(itemRequest.getIdProduct()).get();
            productEntity.setProductStock(productEntity.getProductStock()-itemRequest.getQuantitySelected());
            if (productEntity.getProductStock()==0){
                productService.deleteProduct(productEntity.getIdProduct());
            }
        }
    }

    public Integer countBid() {
        try {
            // Use Optional to handle the possibility of null value
            Optional<Integer> countOptional = Optional.ofNullable(bidRepository.countBid());


            return countOptional.orElseThrow(() -> new RuntimeException("Count of Bid is null"));

        } catch (Exception e) {

            e.printStackTrace();


            throw new RuntimeException("Failed to count users. Reason: " + e.getMessage());
        }
    }

}
