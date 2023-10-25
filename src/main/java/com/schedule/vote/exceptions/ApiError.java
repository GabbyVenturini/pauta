package com.schedule.vote.exceptions;

public class ApiError {
    private int statusCode;
    private String error;
    private Long timestamp;

    public ApiError(int statusCode, String error, Long timestamp) {
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
