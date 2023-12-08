package com.example.ecommers.repository;


import com.example.ecommers.model.ItemBiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface I_ItemBidRepository extends JpaRepository<ItemBiEntity, Long> {
}
