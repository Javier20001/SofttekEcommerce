
package com.example.ecommers.controller;

import com.example.ecommers.model.UserEntity;
import com.example.ecommers.service.UserService;
import com.example.ecommers.serviceInterface.I_UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PutMapping("/update")
    public ResponseEntity<?> updateUser( @RequestBody UserEntity newUser) {
        try {

            return new ResponseEntity<>(userService.updateUser(newUser.getId(), newUser), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
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

    @GetMapping("/count")
    public ResponseEntity<?> countUsers(){
        try{
            return new ResponseEntity<>(userService.countUsers(), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>("Users not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/changeRole")
    public ResponseEntity<?> changeRole(@RequestBody UserEntity user){
        try {
            userService.changeRole(user);
            return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
        }catch (RuntimeException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}



