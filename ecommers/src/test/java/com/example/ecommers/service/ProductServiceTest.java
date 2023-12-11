package com.example.ecommers.service;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.repository.I_ProductRepository;
import com.example.ecommers.service.ProductService;
import com.example.ecommers.serviceInterface.I_CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceTest {

    @Mock
    private I_ProductRepository productRepository;
    @Mock
    private I_CategoryService categoryService;
    @InjectMocks
    private ProductService sut;

    private ProductEntity product;

    @BeforeEach
    void setUp() {
        product = new ProductEntity(
                1L,
                "Pendrive",
                10,
                "img.jpg",
                new BigDecimal(1000),
                Boolean.TRUE,
                new CategoryEntity(
                        1L,
                        "Pendrives",
                        Boolean.TRUE
                )
        );
    }

    @Test
    @DisplayName("Given a list with products when product service call findAll method then return a list of products")
    void getAllProducts() {
        //Given
        final List<ProductEntity> expected = List.of(product);
        when(productRepository.findAll()).thenReturn(expected);

        //When
        final List<ProductEntity> result = sut.getAllProducts();

        //Then
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(expected.size(), result.size())
        );
    }

    @Test
    void getProductById() {
        final Long id = 1L;
        when(productRepository.getReferenceById(id)).thenReturn(product);

        final Optional<ProductEntity> result = sut.getProductById(id);

        assertAll(
                () -> assertNotNull(result)
        );
    }

    @Test
    @DisplayName("Given a product when product service calls saveProduct method then product should be saved")
    void saveProduct() {


        // Mocking behavior for the saveCategory method
        when(sut.saveProduct(product)).thenReturn(product);

        // Mocking behavior for the productRepository.save method
        when(productRepository.save(product)).thenReturn(product);

        // When
        ProductEntity savedProduct = sut.saveProduct(product);

        // Then
        assertAll(
                () -> assertNotNull(savedProduct),
                () -> assertTrue(savedProduct.getStatus()) // Ensure status is set to true
                // Add more assertions if needed
        );
    }



}