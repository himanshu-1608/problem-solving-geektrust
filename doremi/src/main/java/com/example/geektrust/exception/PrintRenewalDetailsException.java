package com.example.geektrust.exception;

public class PrintRenewalDetailsException extends Exception {

    private final String message;

    public PrintRenewalDetailsException(String message) {
        super();
        this.message = message;
    }

    public String getDisplayMessage() {
        return this.message;
    }

}
