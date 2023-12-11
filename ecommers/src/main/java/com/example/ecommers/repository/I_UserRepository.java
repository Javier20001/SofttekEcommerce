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

    //Busca dentro de la tabla de usuario el mail que se le pasa.
    @Query("SELECT p FROM UserEntity p WHERE LOWER(p.email) = LOWER(:email) AND p.status = true")
    Optional<UserEntity> findByEmail(@Param("email") String email);
    @Query("SELECT p FROM UserEntity p WHERE LOWER(p.userName) = LOWER(:userName) AND p.status = true")
    Optional<UserEntity> findByUserName(@Param("userName") String userName);
    @Query("SELECT p FROM UserEntity p WHERE p.resetToken = :token AND p.status = true AND p.expirationDate > CURRENT_TIMESTAMP")
    Optional<UserEntity> findByToken(@Param("token") String token);
}
