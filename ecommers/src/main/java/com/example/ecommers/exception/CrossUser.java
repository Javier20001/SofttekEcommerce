package com.example.ecommers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CrossUser extends ResponseStatusException {

    public CrossUser() {
        super(HttpStatus.FORBIDDEN, "Only ADMIN can view cross user info");
    }
}
