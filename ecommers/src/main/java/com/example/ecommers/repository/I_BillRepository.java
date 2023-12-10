package com.example.ecommers.repository;


import com.example.ecommers.model.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing product entities in the database.
 * Extends JpaRepository for basic CRUD operations on the BidEntity.
 *
 * @Repository Indicates that this interface is a Spring Data repository.
 * @see org.springframework.data.jpa.repository.JpaRepository
 */

@Repository
public interface I_BillRepository extends JpaRepository<BillEntity, Long> {

    @Query("SELECT COUNT(b) FROM BillEntity b")
    Integer countBill();
}
