package com.example.ecommers.controller;

import com.example.ecommers.config.SecurityConfig;
import com.example.ecommers.dto.*;
import com.example.ecommers.exception.CustomHandler;
import com.example.ecommers.model.UserEntity;
import com.example.ecommers.security.JwtUtil;
import com.example.ecommers.service.AuthServiceImpl;
import com.example.ecommers.service.UserService;
import com.example.ecommers.serviceInterface.I_AuthService;
import com.example.ecommers.serviceInterface.I_UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the REST controller for handling authentication and user registration.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/auth")

@RequiredArgsConstructor
public class AuthController {


    private final AuthServiceImpl authService;


    private final JwtUtil jwtUtil;


    private final SecurityConfig securityConfig;


    /**
     * Endpoint for user login.
     *
     * @param loginUserDto The data transfer object containing user login credentials.
     * @return ResponseEntity with a JWT token in case of successful authentication, or an error message if authentication fails.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDTO loginUserDto) {
        try {
            UserEntity currentUser = authService.findByEmail(loginUserDto.getEmail()).get();
            return ResponseEntity.ok()
                    .body(authService.generateToken(loginUserDto.getEmail(),loginUserDto.getPassword(),currentUser));
        } catch (BadCredentialsException e) {
            // Return an error response if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    /**
     * Endpoint for user registration.
     *
     * @param registerUserDto The data transfer object containing user registration details.
     * @return ResponseEntity with a success message in case of successful registration, or an error message if registration fails.
     */
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterUserDTO registerUserDto) {
        try {
            UserEntity user = new UserEntity(0L,registerUserDto.getUserName(), registerUserDto.getEmail(), registerUserDto.getPassword() , null,null,authService.setRole(registerUserDto.getRoles()),true);
            authService.save(registerUserDto);
            return ResponseEntity.ok().body(authService.generateToken(registerUserDto.getEmail(), registerUserDto.getPassword(),user));
        } catch (CustomHandler | HandlerMethodValidationException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> resetRequest(@PathVariable String email){
        try{
            authService.resetRequest(email);
            return new ResponseEntity<>("Email sent!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Email not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/newpassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordUserDTO resetPasswordUserDTO){
        try {
            authService.resetPassword(resetPasswordUserDTO);
            return new ResponseEntity<>("Password updated!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("expired token", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for user logout, invalidating the current token.
     *
     * @param request The HttpServletRequest for the current user session.
     * @return ResponseEntity indicating successful logout.
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        securityConfig.invalidateToken();
        return ResponseEntity.ok("Logout successful");
    }
}