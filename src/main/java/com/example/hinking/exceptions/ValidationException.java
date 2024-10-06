package com.example.hinking.exceptions;


public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}