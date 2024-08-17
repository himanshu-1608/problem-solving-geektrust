package com.example.geektrust.exception;

public class StartSubscriptionException extends Exception {

    private final String message;

    public StartSubscriptionException(String message) {
        super();
        this.message = message;
    }

    public String getDisplayMessage() {
        return this.message;
    }

}
