package com.example.ecommers.exception;

import java.util.Date;

/**
 * CustomedHandler is a custom exception class that extends RuntimeException.
 * It represents a personalized exception with an associated date.
 */
public class CustomHandler extends RuntimeException {

    private Date date;

    /**
     * Constructs a new CustomedHandler object with the given error message.
     *
     * @param message The error message associated with the exception.
     */
    public CustomHandler(String message) {
        super(message);
        setDate(new Date());
    }

    /**
     * Gets the date associated with the exception.
     *
     * @return The date when the exception occurred.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date associated with the exception.
     *
     * @param date The date when the exception occurred.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Generates a string representation of the CustomedHandler object.
     *
     * @return A string representation containing the associated date.
     */
    @Override
    public String toString() {
        return "CustomedHandler{" +
                "date=" + date +
                '}';
    }
}
