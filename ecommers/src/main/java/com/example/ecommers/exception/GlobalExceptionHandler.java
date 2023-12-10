package com.example.ecommers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.sasl.AuthenticationException;

/**
 * Clase de manejo global de excepciones para la aplicación.
 * Anotada con {@code @ControllerAdvice} para proporcionar un manejo centralizado de excepciones en toda la aplicación.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        // Log the exception or perform any other necessary actions
        String errorMessage = "Authentication failed: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }
    // Additional exception handlers for specific authentication issues
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        String errorMessage = "Invalid username or password";
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        // Log the exception or perform any other necessary actions
        String errorMessage = "An unexpected error occurred. Please try again later.";
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<String> handleDisabledException(DisabledException ex) {
        String errorMessage = "Account is disabled";
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
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
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        // Obtener mensajes de error
        String errores = result.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((mensaje1, mensaje2) -> mensaje1 + ", " + mensaje2)
                .orElse("Error de validación");

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
    /**
     * Manejador de excepciones para la excepción {@code UserAlrdyExist}.
     * Retorna una respuesta con el mensaje de error y el código de estado HTTP {@code BAD_REQUEST}.
     *
     * @param ex La excepción {@code UserAlrdyExist} que se está manejando.
     * @return Una respuesta con el mensaje de error y el código de estado HTTP {@code BAD_REQUEST}.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlrdyExist.class)
    public ResponseEntity<String> handleUserAlreadyExistException(UserAlrdyExist ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductAlrdyExist.class)
    public ResponseEntity<String> handleProductAlrdyExistException(ProductAlrdyExist ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
