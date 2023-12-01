package com.example.ecommers.repository;

import com.example.ecommers.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositorio para la entidad RoleEntity, permite gestionar operaciones relacionadas con la entidad Role
 */
@Repository
public interface I_RoleRepository extends JpaRepository<RoleEntity, Long> {
    
}
