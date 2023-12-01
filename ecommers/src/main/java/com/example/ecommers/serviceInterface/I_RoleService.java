/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.ecommers.serviceInterface;

import com.example.ecommers.model.RoleEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Carlos
 */
public interface I_RoleService {
    
    /**
     * Retrieves a list of all role.
     *
     * @return List<RoleEntity> A list of all roles.
     * @see com.example.ecommers.model.RoleEntity
     */
    List<RoleEntity> getAllRoleEntity();
    
    /**
     * Retrieves a product by its identifier.
     *
     * @param id The identifier of the role.
     * @return Optional<RoleEntity> An Optional containing the role, or empty if not found.
     * @see com.example.ecommers.model.RoleEntity
     */
    Optional<RoleEntity> getRoleById(Long id);
    
    /**
     * Saves a new product.
     *
     * @param role The role entity to be saved.
     * @return RoleEntity The saved role.
     * @see com.example.ecommers.model.RoleEntity
     */
    RoleEntity saveRole(RoleEntity role);
    
    /**
     * Updates an existing product.
     *
     * @param id        The identifier of the role to be updated.
     * @param role The updated role entity.
     * @return RoleEntity The updated role.
     * @throws RuntimeException if the role with the specified ID is not found.
     * @see com.example.ecommers.model.RoleEntity
     */
    RoleEntity updateRole(Long id, RoleEntity role);
    
    
    
    
        
    
    
    
    
    
}
