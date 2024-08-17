package com.example.geektrust.domain_models;

import com.example.geektrust.constants.SubscriptionPlanCategory;
import com.example.geektrust.constants.SubscriptionPlanType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionPlanTest {

    @Test
    @DisplayName("Calculations should work good")
    void shouldProvideCorrectDuration() {
        int durationForPlanTypeFree = SubscriptionPlan.getDurationForPlanType(SubscriptionPlanType.FREE);
        assertEquals(1, durationForPlanTypeFree);
        int durationForPlanTypePersonal = SubscriptionPlan.getDurationForPlanType(SubscriptionPlanType.PERSONAL);
        assertEquals(1, durationForPlanTypePersonal);
        int durationForPlanTypePremium = SubscriptionPlan.getDurationForPlanType(SubscriptionPlanType.PREMIUM);
        assertEquals(3, durationForPlanTypePremium);
    }

    @Test
    @DisplayName("Should instantiate properly")
    void shouldInstantiateProperly() {
        SubscriptionPlan subscriptionPlan = new SubscriptionPlan(SubscriptionPlanCategory.MUSIC, SubscriptionPlanType.PREMIUM);
        assertEquals(subscriptionPlan.getPlanCategory(), SubscriptionPlanCategory.MUSIC);
        assertEquals(subscriptionPlan.getPlanType(), SubscriptionPlanType.PREMIUM);
    }

    @Test
    @DisplayName("getPriceForPlanCategoryAndPlanType should give right value")
    void shouldProvideExactValues() {
        assertEquals(0.00, SubscriptionPlan.getPriceForPlanCategoryAndPlanType(SubscriptionPlanCategory.MUSIC, SubscriptionPlanType.FREE), 0.0001);
        assertEquals(100.00, SubscriptionPlan.getPriceForPlanCategoryAndPlanType(SubscriptionPlanCategory.MUSIC, SubscriptionPlanType.PERSONAL), 0.0001);
        assertEquals(250.00, SubscriptionPlan.getPriceForPlanCategoryAndPlanType(SubscriptionPlanCategory.MUSIC, SubscriptionPlanType.PREMIUM), 0.0001);

        assertEquals(0.00, SubscriptionPlan.getPriceForPlanCategoryAndPlanType(SubscriptionPlanCategory.VIDEO, SubscriptionPlanType.FREE), 0.0001);
        assertEquals(200.00, SubscriptionPlan.getPriceForPlanCategoryAndPlanType(SubscriptionPlanCategory.VIDEO, SubscriptionPlanType.PERSONAL), 0.0001);
        assertEquals(500.00, SubscriptionPlan.getPriceForPlanCategoryAndPlanType(SubscriptionPlanCategory.VIDEO, SubscriptionPlanType.PREMIUM), 0.0001);

        assertEquals(0.00, SubscriptionPlan.getPriceForPlanCategoryAndPlanType(SubscriptionPlanCategory.PODCAST, SubscriptionPlanType.FREE), 0.0001);
        assertEquals(100.00, SubscriptionPlan.getPriceForPlanCategoryAndPlanType(SubscriptionPlanCategory.PODCAST, SubscriptionPlanType.PERSONAL), 0.0001);
        assertEquals(300.00, SubscriptionPlan.getPriceForPlanCategoryAndPlanType(SubscriptionPlanCategory.PODCAST, SubscriptionPlanType.PREMIUM), 0.0001);
    }
}