package com.example.ecommers.repository;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.model.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para gestionar operaciones relacionadas con la entidad de categoría ({@link CategoryEntity}).
 * Extiende {@link JpaRepository} proporcionando operaciones básicas de CRUD.
 */
@Repository
public interface I_CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
