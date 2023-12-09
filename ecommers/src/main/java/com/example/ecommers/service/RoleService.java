package com.example.ecommers.service;

import com.example.ecommers.model.RoleEntity;
import com.example.ecommers.repository.I_RoleRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommers.serviceInterface.I_RoleService;


/**
 * Service class implementing the business logic for managing role.
 *
 * This class is annotated with @Service to indicate that it is a service component in the Spring application context.
 * It implements the I_RoleService interface to provide the required functionality.
 *
 * @Service Indicates that this class is a Spring service component.
 * @see com.example.ecommers.serviceInterface.I_RoleService
 */
@Service
@RequiredArgsConstructor
public class RoleService implements I_RoleService{
    
    
    /**S
     * Repository interface for accessing and managing role entities in the database.
     */

    private final I_RoleRepository RoleRepository;
    
    /**
     * Retrieves a list of all role.
     *
     * @return List<RoleEntity> A list of all role.
     * @see com.example.ecommers.model.RoleEntity
     * @see com.example.ecommers.repository.I_RoleRepository#findAll()
     */
    @Override
    public List<RoleEntity> getAllRoleEntity() {
        return RoleRepository.findAll();
    }
    
    /**
     * Retrieves a role by its identifier.
     *
     * @param id The identifier of the role.
     * @return Optional<RoleEntity> An Optional containing the role, or empty if not found.
     * @see com.example.ecommers.model.RoleEntity
     * @see com.example.ecommers.repository.I_RoleRepository#findById(Object)
     */
    @Override
    public Optional<RoleEntity> getRoleById(Long id) {
        return RoleRepository.findById(id);
    }
    
    /**
     * Saves a new role.
     *
     * @param role The role entity to be saved.
     * @return RoleEntity The saved role.
     * @see com.example.ecommers.model.RoleEntity
     * @see com.example.ecommers.repository.I_RoleRepository#save(Object)
     */
    @Override
    public RoleEntity saveRole(RoleEntity role) {
        RoleEntity roleEntity = saveRole(role);
        
        return RoleRepository.save(roleEntity);
    }
    
    /**
     * Updates an existing product.
     *
     * @param id         The identifier of the role to be updated.
     * @param role The updated role entity.
     * @return RoleEntity The updated role.
     * @throws RuntimeException if the role with the specified ID is not found.
     * @see com.example.ecommers.model.RoleEntity
     * @see com.example.ecommers.repository.I_RoleRepository#findById(Object)
     * @see com.example.ecommers.repository.I_RoleRepository#save(Object)
     */
    @Override
    public RoleEntity updateRole(Long id, RoleEntity role) {
        return RoleRepository.findById(id).
                map(existingRole ->{
                    existingRole.setName(role.getName());
                   
        return RoleRepository.save(existingRole);
        }).orElseThrow(() -> new RuntimeException("ID Role " + id + "not found"));      
    }

   
}
