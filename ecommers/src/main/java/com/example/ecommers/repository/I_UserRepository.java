package com.example.ecommers.repository;


import com.example.ecommers.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio para gestionar operaciones relacionadas con la entidad de Usuario.
 */
@Repository
public interface I_UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT p FROM UserEntity p WHERE LOWER(p.email) = LOWER(:email) AND p.status = true AND p.email LIKE '%@%'")
    Optional<UserEntity> findUserByEmail(@Param("email") String email);
}