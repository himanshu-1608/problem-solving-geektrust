package com.example.geektrust.exception;

public class AddTopupException extends Exception {

    private final String message;

    public AddTopupException(String message) {
        super();
        this.message = message;
    }

    public String getDisplayMessage() {
        return this.message;
    }

}
