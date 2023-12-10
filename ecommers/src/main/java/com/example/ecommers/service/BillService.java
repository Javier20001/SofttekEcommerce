package com.example.ecommers.service;


import com.example.ecommers.dto.BillDTO;
import com.example.ecommers.model.*;
import com.example.ecommers.repository.I_BillRepository;
import com.example.ecommers.repository.I_UserRepository;
import com.example.ecommers.security.JwtUtil;
import com.example.ecommers.serviceInterface.I_BillService;
import com.example.ecommers.serviceInterface.I_ItemBillService;
import com.example.ecommers.serviceInterface.I_ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class BillService implements I_BillService {



    private final I_BillRepository billRepository;


    private final I_UserRepository userRepository;
    private final I_ItemBillService iItemBillService;
    private final I_ProductService productService;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;
    @Override
    public List<BillEntity> getAllBill() {
        try{
            return billRepository.findAll();
        }catch(Exception e){
            throw new RuntimeException("failure to bring the invoice. Reason: " + e.getMessage());
        }
    }

    @Override
    public Optional<BillEntity> getBillById(Long id) {
        try {
            return billRepository.findById(id);
        }catch( RuntimeException e) {
            throw new RuntimeException("failure to bring the invoice by id. Reason: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public BillEntity saveBill(@NotNull BillDTO billDTO) throws IllegalArgumentException {
        if(billDTO.getId() != null){
            throw new IllegalArgumentException("bill id must be null for save operation.");
        }
        try{
            UserEntity userEntity=createUser(billDTO);
            DirEntity dir=modelMapper.map(billDTO,DirEntity.class);

            BillEntity bid=billRepository.save(BillEntity.builder()
                            .user(userEntity)
                            .dir(dir)
                    .build());
            saveItem(billDTO,bid);
            return bid;
        }catch(Exception e){
            throw new RuntimeException("Error saving bill: " + e.getMessage(), e);
        }
    }
    private UserEntity createUser(BillDTO billDTO){
        UserEntity userEntity=null;
        if (userRepository.findByEmail(jwtUtil.getUserNameToken(billDTO.getToken())).isPresent()){
            userEntity=userRepository.findByEmail(jwtUtil.getUserNameToken(billDTO.getToken())).get();
        }
        return userEntity;
    }
    private void saveItem (BillDTO billDTO, BillEntity bid){
        for (ItemEntity item : billDTO.getLstItem()) {
            ItemBillEntity itemRequest = ItemBillEntity.builder()
                    .idProduct(item.getProduct().getIdProduct())
                    .quantitySelected(item.getQuantitySelected())
                    .bid(bid)
                    .build();
            iItemBillService.saveItem(itemRequest);
            descontarStock(itemRequest);
        }
    }

    private void descontarStock(ItemBillEntity itemRequest){
        if (productService.getProductById(itemRequest.getIdProduct()).isPresent()){
            ProductEntity productEntity=productService.getProductById(itemRequest.getIdProduct()).get();
            productEntity.setProductStock(productEntity.getProductStock()-itemRequest.getQuantitySelected());
            if (productEntity.getProductStock()==0){
                productService.deleteProduct(productEntity.getIdProduct());
            }
        }
    }

    public Integer countBill() {
        try {
            // Use Optional to handle the possibility of null value
            Optional<Integer> countOptional = Optional.ofNullable(billRepository.countBill());


            return countOptional.orElseThrow(() -> new RuntimeException("Count of Bill is null"));

        } catch (Exception e) {

            e.printStackTrace();


            throw new RuntimeException("Failed to count users. Reason: " + e.getMessage());
        }
    }

}
