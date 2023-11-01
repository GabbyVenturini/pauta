package com.schedule.vote.exceptions;

import java.time.LocalDateTime;

public class ApiError {
    private int statusCode;
    private String error;
    private LocalDateTime timestamp;

    public ApiError(int statusCode, String error, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.error = error;
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
