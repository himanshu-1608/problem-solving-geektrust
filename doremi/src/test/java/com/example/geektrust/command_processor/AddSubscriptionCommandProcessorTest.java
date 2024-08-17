package com.example.geektrust.command_processor;

import com.example.geektrust.constants.ExceptionMessage;
import com.example.geektrust.constants.SubscriptionPlanCategory;
import com.example.geektrust.constants.SubscriptionPlanType;
import com.example.geektrust.domain_models.UserSubscription;
import com.example.geektrust.helper.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AddSubscriptionCommandProcessorTest {

    private ICommandProcessor commandProcessor;

    UserSubscription userSubscription;

    @BeforeEach
    void setUp() {
        TestHelper.resetUserSubscriptionSingletonInstance();
        commandProcessor = new AddSubscriptionCommandProcessor();
        userSubscription = UserSubscription.getInstance();
    }

    @Test
    @DisplayName("Subscription Not Present")
    void shouldThrowErrorWhenSubscriptionNotPresent() {
        ICommandProcessor startSubscriptionCommandProcessor = new StartSubscriptionCommandProcessor();
        // Invalid argument so that subscription is not started
        startSubscriptionCommandProcessor.process(null);
        // random arguments so that size check passes
        commandProcessor.process(Arrays.asList("Hello", "World"));
        assertEquals(ExceptionMessage.ADD_SUBSCRIPTION_GENERIC_EXCEPTION_MESSAGE
                + userSubscription.getStartSubscriptionExceptionMessage(), userSubscription.getAddSubscriptionExceptionMessage());
    }

    @Test
    @DisplayName("Null Argument")
    void shouldThrowErrorWhenArgumentAreNull() {
        TestHelper.addValidSubscription();
        commandProcessor.process(null);
        assertEquals(ExceptionMessage.ADD_SUBSCRIPTION_GENERIC_EXCEPTION_MESSAGE +
                ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION, userSubscription.getAddSubscriptionExceptionMessage());
    }

    @Test
    @DisplayName("Argument Size: Size should be equal to or greater than 2")
    void shouldThrowErrorWhenArgumentAreEmpty() {
        TestHelper.addValidSubscription();
        commandProcessor.process(new ArrayList<>());
        assertEquals(ExceptionMessage.ADD_SUBSCRIPTION_GENERIC_EXCEPTION_MESSAGE +
                ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION, userSubscription.getAddSubscriptionExceptionMessage());
    }

    @Test
    @DisplayName("Arguments are invalid")
    void shouldThrowErrorWhenArgumentAreInvalid() {
        TestHelper.addValidSubscription();
        commandProcessor.process(Arrays.asList("Hello", "World"));
        assertEquals(ExceptionMessage.ADD_SUBSCRIPTION_GENERIC_EXCEPTION_MESSAGE +
                ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION, userSubscription.getAddSubscriptionExceptionMessage());
    }

    @Test
    @DisplayName("Duplicate subscription category")
    void shouldThrowErrorWhenDuplicateSubscriptionCategoryPlanAdded() {
        TestHelper.addValidSubscription();
        commandProcessor.process(Arrays.asList(SubscriptionPlanCategory.MUSIC.toString(), SubscriptionPlanType.PERSONAL.toString()));
        assertNull(userSubscription.getAddSubscriptionExceptionMessage());
        commandProcessor.process(Arrays.asList(SubscriptionPlanCategory.MUSIC.toString(), SubscriptionPlanType.PREMIUM.toString()));
        assertEquals(ExceptionMessage.ADD_SUBSCRIPTION_GENERIC_EXCEPTION_MESSAGE +
                ExceptionMessage.DUPLICATE_CATEGORY_EXCEPTION, userSubscription.getAddSubscriptionExceptionMessage());
    }

    @Test
    @DisplayName("Valid Arguments")
    void shouldWorkFineForValidArguments() {
        TestHelper.addValidSubscription();
        commandProcessor.process(Arrays.asList(SubscriptionPlanCategory.MUSIC.toString(), SubscriptionPlanType.PREMIUM.toString()));
        assertNull(userSubscription.getAddSubscriptionExceptionMessage());
        commandProcessor.process(Arrays.asList(SubscriptionPlanCategory.VIDEO.toString(), SubscriptionPlanType.PREMIUM.toString()));
        assertNull(userSubscription.getAddSubscriptionExceptionMessage());
        commandProcessor.process(Arrays.asList(SubscriptionPlanCategory.PODCAST.toString(), SubscriptionPlanType.FREE.toString()));
        assertNull(userSubscription.getAddSubscriptionExceptionMessage());
    }

}