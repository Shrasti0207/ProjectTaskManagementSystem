package com.example.ProjectTaskManagement.Advices;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

// ErrorResponse class represents the structure of error messages returned in API responses.
public class ErrorResponse {
    private final HttpStatus status;
    private final String message;
    private final LocalDateTime timeStamp;

    public ErrorResponse(HttpStatus status, String message, LocalDateTime timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
