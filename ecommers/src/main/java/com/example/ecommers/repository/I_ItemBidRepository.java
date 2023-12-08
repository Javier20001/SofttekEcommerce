package com.example.ecommers.repository;


import com.example.ecommers.model.ItemBidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_ItemBidRepository extends JpaRepository<ItemBidEntity, Long> {
}
