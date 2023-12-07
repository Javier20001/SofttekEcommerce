package com.example.ecommers.controller;

import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.model.RoleEntity;
import com.example.ecommers.serviceInterface.I_RoleService;
import com.google.gson.Gson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vl/admin/role")
@CrossOrigin(origins = "http://localhost:5173")
public class RoleController {
    
    @Autowired
    private I_RoleService service;
    
    @GetMapping("/list")
    public String list(){
        List<RoleEntity> roles = service.getAllRoleEntity();
        Gson gson = new Gson();
        String json = gson.toJson(roles);
        return json;
    }
    
    @PostMapping("/new")
    public ResponseEntity<?> add(@RequestBody RoleEntity role) {
        try {
            service.saveRole(role);
            return new ResponseEntity<>("Se guardó correctamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error al guardar el rol: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/update")
    public ResponseEntity <?> updateRole(@RequestBody RoleEntity role){
        try{
            service.updateRole(role.getId(), role);
        }catch(RuntimeException e){
            e.printStackTrace();
            throw e;
        }
        return new ResponseEntity<>("Se actualizo correctamente", HttpStatus.OK);
    }
    
}
