package com.example.ecommers.controller;

import com.example.ecommers.model.ProductoEntity;
import com.example.ecommers.serviceInterface.ProductoInterface;
import com.google.gson.Gson;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping
public class ProductoController {
    
    @Autowired
    private ProductoInterface service;
    
    @GetMapping("/")
    public String listar() {
        List<ProductoEntity> productos = service.getAllProductos();
        Gson gson = new Gson();
        String json = gson.toJson(productos);
        System.out.println();
        return json;
	}
    
    @PostMapping("/new")
    public ResponseEntity <?> agregar(@RequestBody ProductoEntity producto){
        try{
            service.saveProducto(producto);
        }catch(RuntimeException he){
            throw new RuntimeException("Error en el guardado de producto");
        }
        return new ResponseEntity<>("Se guardo correctamente", HttpStatus.CREATED);
    }
    
   
    @PostMapping("/update")
    public ResponseEntity <?> updateProducto(@RequestBody ProductoEntity producto ){
        try{
            service.updateProducto(producto.getIdProducto(), producto);
        }catch(RuntimeException he){
            throw new RuntimeException("Error al actualizar");
        }
        return new ResponseEntity<>("Se actualizó correctamente", HttpStatus.OK);
        
    }
    
    @PostMapping("/delete/{id}")
    public ResponseEntity <?> deleteProducto(@PathVariable Long id){
        try{
            service.deleteProducto(id);
        }catch(RuntimeException he){
            throw new RuntimeException("Error al eliminar");
        }
        return new ResponseEntity<>("Se eliminó correctamente", HttpStatus.OK);
         
    }
    
    
}
