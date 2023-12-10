package com.example.ecommers.controller;

import com.example.ecommers.config.SecurityConfig;
import com.example.ecommers.dto.*;
import com.example.ecommers.exception.CustomHandler;
import com.example.ecommers.model.UserEntity;
import com.example.ecommers.security.JwtUtil;
import com.example.ecommers.service.AuthServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

/**
 * This class represents the REST controller for handling authentication and user registration.
 */
@RestController
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
            UserEntity currentUser=null;
            if (authService.findByEmail(loginUserDto.getEmail()).isPresent()){
                currentUser= authService.findByEmail(loginUserDto.getEmail()).get();
            }
            return ResponseEntity.ok()
                    .body(authService.generateToken(loginUserDto.getEmail(),loginUserDto.getPassword(),currentUser));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
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