package com.example.ecommers.exception;

public class ProductAlrdyExist extends CustomHandler{
    /**
     * Constructs a new CustomedHandler object with the given error message.
     *
     * @param message The error message associated with the exception.
     */
    public ProductAlrdyExist(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage() + " " + super.getDate();
    }
}
