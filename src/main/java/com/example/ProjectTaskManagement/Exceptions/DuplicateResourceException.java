package com.example.ProjectTaskManagement.Exceptions;

// This class is used to throw an exception when a duplicate resource is found.
public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException(String message) {
        super(message);
    }
}
