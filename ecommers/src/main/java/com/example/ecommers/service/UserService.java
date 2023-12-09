package com.example.ecommers.service;


import com.example.ecommers.dto.DashboardUserDTO;
import com.example.ecommers.model.UserEntity;
import com.example.ecommers.repository.I_UserRepository;
import com.example.ecommers.serviceInterface.I_UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements I_UserService {


    private final I_UserRepository userRepository;

    //Recupera un usuario por su ID
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    //Guarda un usuario
    //Update de usuario existente
    public UserEntity updateUser(Long id, UserEntity newUser) {
        try {
            return userRepository.findById(id)
                    .map(existingUser -> {
                        existingUser.setUserName(newUser.getUserName());
                        existingUser.setEmail(newUser.getEmail());
                        existingUser.setPassword(newUser.getPassword());
                        existingUser.setRoles(newUser.getRoles());
                        existingUser.setStatus(newUser.getStatus());
                        return userRepository.save(existingUser);
                    })
                    .orElseThrow(() -> new RuntimeException("Id User " + id + " not found"));
        }catch (Exception e){
            throw new RuntimeException("Error updating user with id " + id);
        }
    }

    //Borra un usuario
    public void deleteUser(Long id) {
        try {
            userRepository.findById(id)
                    .map(existingUser -> {
                        existingUser.setStatus(false);
                        return userRepository.save(existingUser);
                    })
                    .orElseThrow(() -> new RuntimeException("Id user \" + id + \" not found"));
        }catch (Exception e){
            throw new RuntimeException("Error deleting user with id " + id);
        }
    }
    public List<DashboardUserDTO> listUsers(){
        try{
            return userRepository.findAll().stream()
                    .map(userEntity -> new DashboardUserDTO(
                            userEntity.getId(),
                            userEntity.getUserName(),
                            userEntity.getEmail(),
                            userEntity.getRoles(),
                            userEntity.getStatus()
                    ))
                    .collect(Collectors.toList());
        }catch(Exception e){
            throw new RuntimeException("Error retrieving the Users");
        }
    }
    public DashboardUserDTO getUser(String username){
        try{
            Optional<UserEntity> optUser = userRepository.findByUserName(username);
            if(optUser.isPresent()){
                DashboardUserDTO dashboardUserDTO = new DashboardUserDTO();
                dashboardUserDTO.setId(optUser.get().getId());
                dashboardUserDTO.setUserName(optUser.get().getUserName());
                dashboardUserDTO.setEmail(optUser.get().getEmail());
                dashboardUserDTO.setRoles(optUser.get().getRoles());
                dashboardUserDTO.setStatus(optUser.get().getStatus());
                return dashboardUserDTO;
            }else{
                throw new RuntimeException("User not found");
            }
        }catch(Exception e){
            throw new RuntimeException("Error retrieving the User", e);
        }
    }
}
