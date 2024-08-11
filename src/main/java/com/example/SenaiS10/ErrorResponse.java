package com.example.SenaiS10;


public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private List<String> details;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, String message, List<String> details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

}

