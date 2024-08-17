package com.example.geektrust.command_processor;

import com.example.geektrust.constants.ExceptionMessage;
import com.example.geektrust.constants.TopupPlanType;
import com.example.geektrust.domain_models.UserSubscription;
import com.example.geektrust.helper.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class AddTopupCommandProcessorTest {

    private ICommandProcessor commandProcessor;

    UserSubscription userSubscription;

    @BeforeEach
    void setUp() {
        TestHelper.resetUserSubscriptionSingletonInstance();
        commandProcessor = new AddTopupCommandProcessor();
        userSubscription = UserSubscription.getInstance();
    }

    @Test
    @DisplayName("Subscription Not Present")
    void shouldThrowErrorWhenSubscriptionNotPresent() {
        ICommandProcessor startSubscriptionCommandProcessor = new StartSubscriptionCommandProcessor();
        // Invalid argument so that subscription is not started
        startSubscriptionCommandProcessor.process(null);
        // random arguments so that size check passes
        commandProcessor.process(Collections.singletonList("Hello"));
        assertEquals(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE
                + userSubscription.getStartSubscriptionExceptionMessage(), userSubscription.getAddTopupExceptionMessage());
    }

    @Test
    @DisplayName("Null Argument")
    void shouldThrowErrorWhenArgumentAreNull() {
        TestHelper.addValidSubscription();
        commandProcessor.process(null);
        assertEquals(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE
                + ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION, userSubscription.getAddTopupExceptionMessage());
    }

    @Test
    @DisplayName("Argument Size: Size should be equal to or greater than 2")
    void shouldThrowErrorWhenArgumentAreEmpty() {
        TestHelper.addValidSubscription();
        commandProcessor.process(new ArrayList<>());
        assertEquals(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE
                + ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION, userSubscription.getAddTopupExceptionMessage());
    }

    @Test
    @DisplayName("Arguments are invalid")
    void shouldThrowErrorWhenArgumentAreInvalid() {
        TestHelper.addValidSubscription();
        commandProcessor.process(Arrays.asList("Hello", "World"));
        assertEquals(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE +
                ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION, userSubscription.getAddTopupExceptionMessage());
    }

    @Test
    @DisplayName("Argument 2 is invalid")
    void shouldThrowErrorWhen2ndArgumentAreInvalid() {
        TestHelper.addValidSubscription();
        commandProcessor.process(Arrays.asList(TopupPlanType.FOUR_DEVICE.toString(), "World"));
        assertEquals(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE +
                ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION, userSubscription.getAddTopupExceptionMessage());
    }

    @Test
    @DisplayName("No subscription plan exists")
    void shouldThrowErrorWhenNoSubscriptionPlanExists() {
        TestHelper.addValidSubscription();
        commandProcessor.process(Arrays.asList(TopupPlanType.FOUR_DEVICE.toString(), "3"));
        assertEquals(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE +
                ExceptionMessage.SUBSCRIPTION_NOT_FOUND_EXCEPTION, userSubscription.getAddTopupExceptionMessage());
    }

    @Test
    @DisplayName("Duplicate topup plan exists of same type")
    void shouldThrowErrorWhenDuplicateTopupPlanAdded() {
        TestHelper.addValidSubscription();
        TestHelper.addRandomSubscription();
        commandProcessor.process(Arrays.asList(TopupPlanType.FOUR_DEVICE.toString(), "2"));
        assertNull(userSubscription.getAddTopupExceptionMessage());
        commandProcessor.process(Arrays.asList(TopupPlanType.FOUR_DEVICE.toString(), "3"));
        assertEquals(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE +
                ExceptionMessage.DUPLICATE_TOPUP_EXCEPTION, userSubscription.getAddTopupExceptionMessage());
    }

    @Test
    @DisplayName("Duplicate topup plan exists of different type")
    void shouldThrowErrorWhenDuplicateTopupPlanAddedOfDifferentType() {
        TestHelper.addValidSubscription();
        TestHelper.addRandomSubscription();
        commandProcessor.process(Arrays.asList(TopupPlanType.FOUR_DEVICE.toString(), "4"));
        assertNull(userSubscription.getAddTopupExceptionMessage());
        commandProcessor.process(Arrays.asList(TopupPlanType.TEN_DEVICE.toString(), "3"));
        assertEquals(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE +
                ExceptionMessage.DUPLICATE_TOPUP_EXCEPTION, userSubscription.getAddTopupExceptionMessage());
    }

    @Test
    @DisplayName("Valid Arguments")
    void shouldWorkFineForValidArguments() {
        TestHelper.addValidSubscription();
        TestHelper.addRandomSubscription();
        commandProcessor.process(Arrays.asList(TopupPlanType.FOUR_DEVICE.toString(), "3"));
        assertNull(userSubscription.getAddTopupExceptionMessage());
    }
}