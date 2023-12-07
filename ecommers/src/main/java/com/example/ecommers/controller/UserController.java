
package com.example.ecommers.controller;

import com.example.ecommers.model.UserEntity;
import com.example.ecommers.service.UserService;
import com.example.ecommers.serviceInterface.I_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    UserService userService;


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserEntity newUser) {
        try {
            UserEntity updatedUser = userService.updateUser(id, newUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Email not found", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/list")
    public ResponseEntity<?> listUsers(){
        try{
            return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Users not found", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/list/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username){
        try{
            return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }
}



