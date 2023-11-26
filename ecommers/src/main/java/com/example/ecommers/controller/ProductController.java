package com.example.ecommers.controller;

import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.serviceInterface.ProductInterface;
import com.google.gson.Gson;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping
public class ProductController {
    
    @Autowired
    private ProductInterface service;
    
    @GetMapping("/")
    public String list() {
        List<ProductEntity> products = service.getAllProducts();
        Gson gson = new Gson();
        String json = gson.toJson(products);
        System.out.println();
        return json;
	}
    
    @PostMapping("/new")
    public ResponseEntity <?> add(@RequestBody ProductEntity product){
        try{
            service.saveProduct(product);
        }catch(RuntimeException he){
            throw new RuntimeException("Error en el guardado de producto");
        }
        return new ResponseEntity<>("Se guardo correctamente", HttpStatus.CREATED);
    }
    
   
    @PostMapping("/update")
    public ResponseEntity <?> updateProduct(@RequestBody ProductEntity product ){
        try{
            service.updateProduct(product.getIdProduct(), product);
        }catch(RuntimeException he){
            throw new RuntimeException("Error al actualizar");
        }
        return new ResponseEntity<>("Se actualizó correctamente", HttpStatus.OK);
        
    }
    
    @PostMapping("/delete/{id}")
    public ResponseEntity <?> deleteProduct(@PathVariable Long id){
        try{
            service.deleteProduct(id);
        }catch(RuntimeException he){
            throw new RuntimeException("Error al eliminar");
        }
        return new ResponseEntity<>("Se eliminó correctamente", HttpStatus.OK);
         
    }
    
    
}
