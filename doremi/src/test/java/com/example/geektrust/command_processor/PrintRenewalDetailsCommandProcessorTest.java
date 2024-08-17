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

class PrintRenewalDetailsCommandProcessorTest {

    private ICommandProcessor commandProcessor;

    UserSubscription userSubscription;

    @BeforeEach
    void setUp() {
        TestHelper.resetUserSubscriptionSingletonInstance();
        commandProcessor = new PrintRenewalDetailsCommandProcessor();
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
        assertEquals(ExceptionMessage.SUBSCRIPTION_NOT_FOUND_EXCEPTION, userSubscription.getPrintRenewalDetailsException());
    }

    @Test
    @DisplayName("Subscription Plan Not Present")
    void shouldThrowErrorWhenSubscriptionPlanNotPresent() {
        TestHelper.addValidSubscription();
        // random arguments so that size check passes
        commandProcessor.process(Collections.singletonList("Hello"));
        assertEquals(ExceptionMessage.SUBSCRIPTION_NOT_FOUND_EXCEPTION, userSubscription.getPrintRenewalDetailsException());
    }

    @Test
    @DisplayName("Valid Arguments")
    void shouldWorkFineForValidArguments() {
        TestHelper.addValidSubscription();
        TestHelper.addRandomSubscription();
        TestHelper.addRandomTopupPlan();
        commandProcessor.process(new ArrayList<>());
        assertNull(userSubscription.getPrintRenewalDetailsException());
    }

    @Test
    @DisplayName("Valid Arguments without topup")
    void shouldWorkFineForValidArgumentsWithoutTopupPlan() {
        TestHelper.addValidSubscription();
        TestHelper.addRandomSubscription();
        commandProcessor.process(new ArrayList<>());
        assertNull(userSubscription.getPrintRenewalDetailsException());
    }
}