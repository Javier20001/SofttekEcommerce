package com.example.ecommers.controller;

import com.example.ecommers.dto.LoginUserDTO;
import com.example.ecommers.dto.RegisterUserDTO;
import com.example.ecommers.model.RoleEntity;
import com.example.ecommers.model.RolesName;
import com.example.ecommers.model.UserEntity;
import com.example.ecommers.service.AuthServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
class AuthControllerTest {

    private final static String BASE_URL = "/api/v1/auth";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthServiceImpl authService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private RegisterUserDTO register;

    private LoginUserDTO login;

    @Mock
    private PasswordEncoder pass;



    @BeforeEach
    void setUp() {
       register = new RegisterUserDTO();
               register.setUserName("testUser3");
               register.setPassword("password");
               register.setEmail("12345@example.com.ar");
               register.setRoles(List.of(new RoleEntity(1L , RolesName.USER).getId()));
               login = new LoginUserDTO();
               login.setEmail("123@example.com.ar");
               login.setPassword("password");
    }

    @Test
    void login() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/login")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(maptoJson(login)))
                        .andReturn();
        assertAll(

                ()  ->  assertNotNull(result),
                ()  ->  assertEquals(200, result.getResponse().getStatus())
        );
    }

    @Test
    void createUser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/register")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(maptoJson(register)))
                        .andReturn();

        assertAll(

                ()  ->  assertNotNull(result),
                ()  ->  assertEquals(200, result.getResponse().getStatus())
        );

    }

    @Test
    void logout() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/logout")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertAll(

                ()  ->  assertNotNull(result),
                ()  ->  assertEquals(200, result.getResponse().getStatus())
        );
    }

    private String maptoJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(object);
    }
}