package com.example.ecommers.service;

import com.example.ecommers.model.*;
import com.example.ecommers.repository.I_UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private I_UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserEntity sut;
    @BeforeEach
    public void setUp() {
        // Configuración común antes de cada prueba para UserEntity

        sut = new UserEntity();
        sut.setId(1L);
        sut.setUserName("testUser");
        sut.setEmail("test@example.com");
        sut.setPassword("password");
        sut.setResetToken("resetToken");
        sut.setExpirationDate(LocalDateTime.now().plusDays(1));
        sut.setRoles(List.of(new RoleEntity(1L ,  RolesName.USER)));
        sut.setStatus(true);

    }

    @Test
    public void testGetUserById() {






        when(userRepository.findById(1L)).thenReturn(Optional.of(sut));

        // Llamada al método de servicio
        Optional<UserEntity> result = userService.getUserById(sut.getId());

        // Verificación
        assertTrue(result.isPresent());

        assertEquals(sut, result.get());


    }

    @Test
    public void testDeleteUser_Success() {
        // Arrange
        Long userId = 1L;
        UserEntity existingUser = new UserEntity();
        existingUser.setId(userId);
        existingUser.setStatus(true);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(existingUser);

    }


    @Test
    public void testUpdateUser_Success() {
        // Arrange
        Long userId = 1L;
        UserEntity existingUser = new UserEntity();
        existingUser.setId(userId);
        existingUser.setUserName("oldUserName");
        existingUser.setEmail("old@example.com");
        existingUser.setPassword("oldPassword");
        existingUser.setRoles(Arrays.asList(new RoleEntity(1L, RolesName.USER)));
        existingUser.setStatus(true);

        UserEntity newUser = new UserEntity();
        newUser.setId(userId);
        newUser.setUserName("newUserName");
        newUser.setEmail("new@example.com");
        newUser.setPassword("newPassword");
        newUser.setRoles(Arrays.asList(new RoleEntity(2L, RolesName.ADMIN)));
        newUser.setStatus(false);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UserEntity updatedUser = userService.updateUser(userId, newUser);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(existingUser);
        assertEquals(newUser.getUserName(), updatedUser.getUserName());
        assertEquals(newUser.getEmail(), updatedUser.getEmail());
        assertEquals(newUser.getPassword(), updatedUser.getPassword());
        assertEquals(newUser.getRoles(), updatedUser.getRoles());
        assertEquals(newUser.getStatus(), updatedUser.getStatus());
    }



}
