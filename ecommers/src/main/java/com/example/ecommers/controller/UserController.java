package com.example.ecommers.controller;

import com.example.ecommers.model.UserEntity;
import com.example.ecommers.service.UserService;
import com.example.ecommers.serviceInterface.I_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final I_UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Agrega un nuevo usario
    @PostMapping("/new")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {
        UserEntity savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //Update de usuario existente
    @PutMapping("update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity newUser) {
        UserEntity updatedUser = userService.updateUser(id, newUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //Delete de usuario
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
