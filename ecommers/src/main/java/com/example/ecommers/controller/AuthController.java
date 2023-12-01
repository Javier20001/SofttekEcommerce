package com.example.ecommers.controller;

import com.example.ecommers.dto.LoginResponseDTO;
import com.example.ecommers.dto.LoginResponseUserDTO;
import com.example.ecommers.dto.LoginUserDTO;
import com.example.ecommers.dto.RegisterUserDTO;
import com.example.ecommers.model.UserEntity;
import com.example.ecommers.security.JwtUtil;
import com.example.ecommers.service.AuthServiceImpl;
import com.example.ecommers.serviceInterface.I_AuthService;
import com.example.ecommers.serviceInterface.I_UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the REST controller for handling authentication and user registration.
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@NoArgsConstructor
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Endpoint for user login.
     *
     * @param loginUserDto The data transfer object containing user login credentials.
     * @return ResponseEntity with a JWT token in case of successful authentication, or an error message if authentication fails.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDTO loginUserDto) {
        try {
            // Authenticate the user using provided credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUserDto.getEmail(),
                            loginUserDto.getPassword()
                    )
            );

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtUtil.generateAccesToken(loginUserDto.getEmail());

            // Return JWT token and user details in the response
            UserEntity currentUser = authService.findByEmail(loginUserDto.getEmail()).get();
            LoginResponseUserDTO loginResponseUserDTO = new LoginResponseUserDTO(
                    currentUser.getUserName(),
                    currentUser.getRoles().stream().map(role -> String.valueOf(role.getName())).toList()
            );
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(
                    token,
                    loginResponseUserDTO
            );
            return ResponseEntity.ok()
                    .body(loginResponseDTO);
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
            UserEntity user = new UserEntity(0L,registerUserDto.getUserName(), registerUserDto.getEmail(), registerUserDto.getPassword() , authService.setRole(registerUserDto.getRoles()),true);
            authService.save(registerUserDto);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            registerUserDto.getEmail(),
                            registerUserDto.getPassword()
                    )
            );
            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtUtil.generateAccesToken(registerUserDto.getEmail());

            LoginResponseUserDTO loginResponseUserDTO = new LoginResponseUserDTO(
                    user.getUserName(),
                    user.getRoles().stream().map(role -> String.valueOf(role.getName())).toList()
            );
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(
                    token,
                    loginResponseUserDTO
            );
            return ResponseEntity.ok().body(loginResponseDTO);
        } catch (RuntimeException re) {
            re.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("error", "This user already exists"));
        }
    }

    /**
     *
     * Class to handle @Valid exception and personalize error message
     *
     * @param ex type: MethodArgumentNotValidException, exception thrown by @Valid
     * @return Personalized error message
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = "Bad Request";
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}