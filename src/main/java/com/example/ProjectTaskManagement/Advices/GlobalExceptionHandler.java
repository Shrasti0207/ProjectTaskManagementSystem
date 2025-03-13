package com.example.ProjectTaskManagement.Advices;

import com.example.ProjectTaskManagement.Exceptions.BadRequestException;
import com.example.ProjectTaskManagement.Exceptions.DuplicateResourceException;
import com.example.ProjectTaskManagement.Exceptions.ResourceNotFoundException;
import com.example.ProjectTaskManagement.Exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

// Global exception handler for handling custom exceptions across the application.
@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handles ResourceNotFoundException when a requested resource is not found.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND,
                resourceNotFoundException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handles BadRequestException when an invalid request is made.
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException badRequestException){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,
                badRequestException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handles DuplicateResourceException when a resource is already present.
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException duplicateResourceException){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT,
                duplicateResourceException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Handles ValidationException when a validation error occurs.
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException validationException){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,
                validationException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
