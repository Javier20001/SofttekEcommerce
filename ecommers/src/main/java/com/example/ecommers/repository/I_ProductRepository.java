package com.example.ecommers.repository;

import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;


/**
 * Repository interface for accessing and managing product entities in the database.
 * Extends JpaRepository for basic CRUD operations on the ProductEntity.
 *
 * @Repository Indicates that this interface is a Spring Data repository.
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface I_ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Consulta personalizada usando la cláusula LIKE
    @Query("SELECT p FROM ProductEntity p WHERE LOWER(p.productName) LIKE LOWER(concat('%', :name, '%')) AND LENGTH(:name) >= 3 AND p.status = true")
    List<ProductEntity> findByProductNameContainingIgnoreCase(@Param("name") String name);


    @Query("SELECT p FROM ProductEntity  p where (p.status = true ) ")
    List<ProductEntity> findByStatusTrue ();
    Optional<ProductEntity> findByProductNameIgnoreCaseAndStatus(String productName, boolean status);
}
