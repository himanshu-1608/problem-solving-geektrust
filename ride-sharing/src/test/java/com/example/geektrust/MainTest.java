package com.example.geektrust;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.exception.DomainModelConstraintException;
import com.example.geektrust.exception.InvalidArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

    @Test
    @DisplayName("Null Arguments")
    public void shouldThrowErrorForNullArguments() {
        assertThrows(InvalidArgumentException.class, () -> {
            Main.main(new String[]{null});
        }, Constants.INVALID_ARGUMENT_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("Empty Arguments")
    public void shouldThrowErrorForEmptyArguments() {
        assertThrows(InvalidArgumentException.class, () -> {
            Main.main(new String[]{""});
        }, Constants.INVALID_ARGUMENT_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("Invalid File Location")
    public void shouldThrowErrorForWrongFileLocation() {
        assertThrows(InvalidArgumentException.class, () -> {
            Main.main(new String[]{"sample_input/input.txt"});
        }, Constants.INVALID_ARGUMENT_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("Valid Arguments")
    public void shouldWorkForValidArguments() throws DomainModelConstraintException, InvalidArgumentException {
        Main.main(new String[]{"sample_input/input1.txt"});
    }
}