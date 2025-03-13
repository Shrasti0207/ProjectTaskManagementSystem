package com.example.ProjectTaskManagement.Exceptions;

// This class is used to throw an exception when a bad request is made by the user.
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
