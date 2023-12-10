package com.example.ecommers.repository;


import com.example.ecommers.model.ItemBillEntity;
import com.example.ecommers.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface I_ItemBillRepository extends JpaRepository<ItemBillEntity, Long> {
    @Query("SELECT i.idProduct, SUM(i.quantitySelected) AS totalSaled FROM ItemBillEntity i GROUP BY i.idProduct ORDER BY totalSaled DESC LIMIT 1")
    Long findProductIdMostlySaled();
}
