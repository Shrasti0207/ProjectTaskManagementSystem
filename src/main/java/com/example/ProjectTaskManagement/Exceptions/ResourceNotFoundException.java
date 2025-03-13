package com.example.ProjectTaskManagement.Exceptions;

// This class is used to throw an exception when a resource is not found.
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
