package com.example.ecommers.controller;

import com.example.ecommers.model.CategoryEntity;
import com.example.ecommers.model.ProductEntity;
import com.example.ecommers.model.RoleEntity;
import com.example.ecommers.model.UserEntity;
import com.example.ecommers.security.JwtUtil;
import com.example.ecommers.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
class ProductControllerTest {

    private final static String BASE_URL = "/api/v1/product";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;


    @Autowired
    private WebApplicationContext webApplicationContext;

    private ProductEntity product;

    @BeforeEach
    void setUp() {
        product = new ProductEntity(
                1L,
                "Pendrive",
                10,
                "img.jpg",
                "description",
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
    void list() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/list")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertEquals(200, mockMvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "carlos", roles = "ADMIN")
    void add() throws Exception {
        MvcResult result = (MvcResult) mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/new")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(maptoJson(product)))
                        .andReturn();

        assertAll(

                ()  ->  assertNotNull(result),
                ()  ->  assertEquals(201, result.getResponse().getStatus())
        );
    }

    @Test
    @WithMockUser(username = "carlos", roles = "ADMIN")
    void updateProduct() throws Exception {
        MvcResult result = (MvcResult) mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/update")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(maptoJson(product)))
                .andReturn();


        assertAll(

                ()  ->  assertNotNull(result),
                ()  ->  assertEquals(200, result.getResponse().getStatus())
        );
    }

    @Test
    @WithMockUser(username = "carlos", roles = "ADMIN")
    void deleteProduct() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/delete/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andReturn();
        assertAll(

                ()  ->  assertNotNull(result),
                ()  ->  assertEquals(200, result.getResponse().getStatus())
        );

    }

    @Test
    void find() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/find/log")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertAll(

                ()  ->  assertNotNull(result),
                ()  ->  assertEquals(200, result.getResponse().getStatus())
        );
    }

    private String maptoJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}