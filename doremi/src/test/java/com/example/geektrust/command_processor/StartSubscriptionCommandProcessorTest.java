package com.example.geektrust.command_processor;

import com.example.geektrust.constants.ExceptionMessage;
import com.example.geektrust.domain_models.UserSubscription;
import com.example.geektrust.helper.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StartSubscriptionCommandProcessorTest {

    private ICommandProcessor commandProcessor;

    UserSubscription userSubscription;

    @BeforeEach
    void setUp() {
        TestHelper.resetUserSubscriptionSingletonInstance();
        commandProcessor = new StartSubscriptionCommandProcessor();
        userSubscription = UserSubscription.getInstance();
    }

    @Test
    @DisplayName("Null Argument")
    void shouldThrowErrorWhenArgumentAreNull() {
        commandProcessor.process(null);
        assertEquals(ExceptionMessage.INVALID_DATE_EXCEPTION, userSubscription.getStartSubscriptionExceptionMessage());
    }

    @Test
    @DisplayName("Empty Argument")
    void shouldThrowErrorWhenArgumentAreEmpty() {
        commandProcessor.process(new ArrayList<>());
        assertEquals(ExceptionMessage.INVALID_DATE_EXCEPTION, userSubscription.getStartSubscriptionExceptionMessage());
    }

    @Test
    @DisplayName("Invalid Date Argument")
    void shouldThrowErrorWhenDateIsInvalidCharacters() {
        commandProcessor.process(Collections.singletonList("Hello World"));
        assertEquals(ExceptionMessage.INVALID_DATE_EXCEPTION, userSubscription.getStartSubscriptionExceptionMessage());
    }

    @Test
    @DisplayName("Valid Date Argument")
    void shouldWorkFineForValidArguments() {
        String dateToTest = "19-12-2001";
        commandProcessor.process(Collections.singletonList(dateToTest));
        assertNull(userSubscription.getStartSubscriptionExceptionMessage());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date assertedDate = sdf.parse(dateToTest);
            assertEquals(assertedDate, userSubscription.getSubscriptionStartDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}