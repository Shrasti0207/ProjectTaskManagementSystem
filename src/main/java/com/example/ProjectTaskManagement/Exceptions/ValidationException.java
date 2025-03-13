package com.example.ProjectTaskManagement.Exceptions;

// This class is used to throw an exception when a validation error occurs.
public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
