package com.example.ecommers.repository;

import com.example.ecommers.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_ProductRepository extends JpaRepository<ProductEntity, Long> {
    // Aca podemos agregar consultas personalizadas
}
