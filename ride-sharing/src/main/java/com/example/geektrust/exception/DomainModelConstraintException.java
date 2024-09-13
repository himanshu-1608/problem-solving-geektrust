package com.example.geektrust.exception;

import com.example.geektrust.constants.Constants;

public class DomainModelConstraintException extends Exception {

    private final String message;

    public DomainModelConstraintException() {
        super();
        this.message = Constants.DOMAIN_MODEL_UPDATE_CONSTRAINT_EXCEPTION_MESSAGE;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
