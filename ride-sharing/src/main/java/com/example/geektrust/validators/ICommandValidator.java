package com.example.geektrust.validators;

import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.CommandArgument;

public interface ICommandValidator {
    // The boolean return value tells whether the command arguments were valid or not
    default boolean validate(CommandArgument commandArgument) throws InvalidArgumentException {
        return true;
    }
}
