package com.example.ecommers.serviceInterface;

import com.example.ecommers.model.ItemBillEntity;
import com.example.ecommers.model.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface I_ItemBillService {

    List<ItemBillEntity> getAllItemsBill();



    Optional<ItemBillEntity> getItemBillById(Long id);


    ItemBillEntity saveItem(ItemBillEntity product);
    ProductEntity productMostSaled();

}
