package com.example.ecommers.repository;

import com.example.ecommers.model.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    // Aca podemos agregar consultas personalizadas
}
