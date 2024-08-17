package com.example.geektrust;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest extends Main {

    @BeforeEach
    void setUp() {}

    @Test
    @DisplayName("Null Arguments")
    public void shouldThrowErrorForNullArguments() {
        assertThrows(RuntimeException.class, () -> {
            Main.main(new String[]{null});
        }, "Exception wile fetching input file location: null");
    }

    @Test
    @DisplayName("Empty Arguments")
    public void shouldThrowErrorForEmptyArguments() {
        assertThrows(RuntimeException.class, () -> {
            Main.main(new String[]{""});
        }, "Exception while fetching input file location: null");
    }

    @Test
    @DisplayName("Invalid File Location")
    public void shouldThrowErrorForWrongFileLocation() {
        assertThrows(RuntimeException.class, () -> {
            Main.main(new String[]{"sample_input/input.txt"});
        });
    }

    @Test
    @DisplayName("Valid Arguments")
    public void shouldWorkForValidArguments() {
        Main.main(new String[]{"sample_input/input1.txt"});
    }

}