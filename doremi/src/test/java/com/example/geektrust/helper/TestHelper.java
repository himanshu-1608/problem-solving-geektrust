package com.example.geektrust.helper;

import com.example.geektrust.command_processor.AddSubscriptionCommandProcessor;
import com.example.geektrust.command_processor.AddTopupCommandProcessor;
import com.example.geektrust.command_processor.ICommandProcessor;
import com.example.geektrust.command_processor.StartSubscriptionCommandProcessor;
import com.example.geektrust.constants.SubscriptionPlanCategory;
import com.example.geektrust.constants.SubscriptionPlanType;
import com.example.geektrust.constants.TopupPlanType;
import com.example.geektrust.domain_models.UserSubscription;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;

public class TestHelper {

    public static void resetUserSubscriptionSingletonInstance() {
        try {
            Field instanceField = UserSubscription.class.getDeclaredField("instance");
            instanceField.setAccessible(true);
            instanceField.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addValidSubscription() {
        ICommandProcessor startSubscriptionCommandProcessor = new StartSubscriptionCommandProcessor();
        startSubscriptionCommandProcessor.process(Collections.singletonList("12-12-2001"));
    }

    public static void addRandomSubscription() {
        ICommandProcessor commandProcessor = new AddSubscriptionCommandProcessor();
        commandProcessor.process(Arrays.asList(SubscriptionPlanCategory.MUSIC.toString(), SubscriptionPlanType.PREMIUM.toString()));
    }

    public static void addRandomTopupPlan() {
        ICommandProcessor commandProcessor = new AddTopupCommandProcessor();
        commandProcessor.process(Arrays.asList(TopupPlanType.FOUR_DEVICE.toString(), "3"));
    }
}
