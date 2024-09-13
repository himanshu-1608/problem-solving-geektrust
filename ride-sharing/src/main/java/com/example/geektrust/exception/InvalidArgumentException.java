package com.example.geektrust.exception;

import com.example.geektrust.constants.Constants;

public class InvalidArgumentException extends Exception {

    private final String message;

    public InvalidArgumentException() {
        super();
        this.message = Constants.INVALID_ARGUMENT_EXCEPTION_MESSAGE;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
