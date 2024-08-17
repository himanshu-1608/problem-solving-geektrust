package com.example.geektrust.exception;

public class AddSubscriptionException extends Exception {

    private final String message;

    public AddSubscriptionException(String message) {
        super();
        this.message = message;
    }

    public String getDisplayMessage() {
        return this.message;
    }

}
