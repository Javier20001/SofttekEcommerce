package com.example.ecommers.serviceInterface;

import com.example.ecommers.model.ItemBidEntity;

import java.util.List;
import java.util.Optional;

public interface I_ItemBidService {

    List<ItemBidEntity> getAllItemsBid();



    Optional<ItemBidEntity> getItemBidById(Long id);


    ItemBidEntity saveItem(ItemBidEntity product);

}
