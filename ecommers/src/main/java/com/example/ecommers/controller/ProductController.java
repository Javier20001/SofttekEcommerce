package com.example.ecommers.controller;

import com.example.ecommers.config.SecurityConfig;
import com.example.ecommers.exception.ProductAlrdyExist;
import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.service.AuthServiceImpl;
import com.example.ecommers.serviceInterface.I_CategoryService;
import com.example.ecommers.serviceInterface.I_ProductService;
import com.google.gson.Gson;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling operations related to products.
 *
 * This class is responsible for managing CRUD (Create, Read, Update, Delete) operations
 * for products and exposes corresponding API endpoints.
 *
 * @RestController Indicates that this class is a controller for handling HTTP requests.
 * @RequestMapping("/api/v1/product") Base mapping for all endpoints in this controller.
 * @CrossOrigin(origins = "http://localhost:5173") Configures Cross-Origin Resource Sharing (CORS) for the controller.
 */
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    /**
     * Service interface for handling product-related business logic.
     */

     private final I_ProductService service;

    /**
     * Retrieves a list of all products.
     *
     * @return String A JSON representation of the list of products.
     * @GetMapping("/list") Mapping for the endpoint to retrieve the list of all products.
     */
    @GetMapping("/list")
    public String list() {
        List<ProductEntity> products = service.findByStatusTrue();
        Gson gson = new Gson();
        String json = gson.toJson(products);
        System.out.println(); // This line seems unnecessary; consider removing or adding a comment explaining its purpose.
        return json;
    }

    /**
     * Adds a new product.
     *
     * @param product The product entity to be saved.
     * @return ResponseEntity<?> A response entity with a success message and HTTP status.
     * @PostMapping("/new") Mapping for the endpoint to add a new product.
     */
    @PostMapping("/new")
    public ResponseEntity<?> add(@RequestBody ProductEntity product) {
        try {
            service.saveProduct(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (ProductAlrdyExist e) {
            // Handle the specific exception thrown by the service
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            // Handle other runtime exceptions, you might want to log them or return a generic error response
            return new ResponseEntity<>("Error at save product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Updates an existing product.
     *
     * @param product The updated product entity.
     * @return ResponseEntity<?> A response entity with a success message and HTTP status.
     * @PostMapping("/update") Mapping for the endpoint to update an existing product.
     */
    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductEntity product ){
        try{
            service.updateProduct(product.getIdProduct(), product);
        }catch (RuntimeException e) {
            e.printStackTrace(); // Adds this line to print the exception trace.
            throw e; // Throws the original exception to maintain previous behavior.
        }
        return new ResponseEntity<>("Update successfully", HttpStatus.OK);
    }

    /**
     * Deletes a product by its identifier.
     *
     * @param id The identifier of the product to be deleted.
     * @return ResponseEntity<?> A response entity with a success message and HTTP status.
     * @PostMapping("/delete/{id}") Mapping for the endpoint to delete a product by its identifier.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try{

            service.deleteProduct(id);

        }catch(RuntimeException he){
            throw new RuntimeException("Could not eliminate");
        }
        return new ResponseEntity<>("Product correctly eliminated", HttpStatus.OK);
    }
    @GetMapping("/find/{name}")
    public String find(@PathVariable String name){
        List<ProductEntity> ltsE = service.findByProductNameContainingIgnoreCase(name);
        Gson gson = new Gson();
        String json = gson.toJson(ltsE);
        return json;
    }

}
