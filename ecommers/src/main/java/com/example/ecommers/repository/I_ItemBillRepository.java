package com.example.ecommers.repository;


import com.example.ecommers.model.ItemBillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_ItemBillRepository extends JpaRepository<ItemBillEntity, Long> {
}
