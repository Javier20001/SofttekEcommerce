package com.example.ecommers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase de manejo global de excepciones para la aplicación.
 * Anotada con {@code @ControllerAdvice} para proporcionar un manejo centralizado de excepciones en toda la aplicación.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
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
