package com.example.ecommers.repository;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gestionar operaciones relacionadas con la entidad de Usuario.
 */
@Repository
public interface I_UserRepository extends JpaRepository<UserEntity, Long> {
}
