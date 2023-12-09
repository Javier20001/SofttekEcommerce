package com.example.ecommers.controller;

import com.example.ecommers.model.RoleEntity;
import com.example.ecommers.model.RolesName;
import com.example.ecommers.model.UserEntity;
import com.example.ecommers.repository.I_UserRepository;
import com.example.ecommers.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest {

    private final static String BASE_URL = "/api/v1/admin/user";


    private final MockMvc mockMvc;



    private UserEntity user;

    @BeforeEach
    void setUp(){
        user = new UserEntity();
        user.setId(1L);
        user.setUserName("testUser");
        user.setEmail("123@example.com.ar");
        user.setPassword("password");
        user.setResetToken("resetToken");
        user.setExpirationDate(LocalDateTime.now().plusDays(1));
        user.setRoles(List.of(new RoleEntity(1L ,  RolesName.USER)));
        user.setStatus(true);

    }

    @Test
    @WithMockUser(username = "carlos", roles = "ADMIN")
    void updateUser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/update/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(maptoJson(user)))
                .andReturn();

                assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    @WithMockUser(username = "carlos", roles = "ADMIN")
    void deleteUser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/delete/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertAll(

                ()  ->  assertNotNull(result),
                ()  ->  assertEquals(200, result.getResponse().getStatus())
        );

    }

    @Test
    @WithMockUser(username = "carlos", roles = "ADMIN")
    void listUsers() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/list")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertEquals(200, mockMvcResult.getResponse().getStatus());

    }

    @Test
    @WithMockUser(username = "carlos", roles = "ADMIN")
    void getUser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/list/testUser")
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