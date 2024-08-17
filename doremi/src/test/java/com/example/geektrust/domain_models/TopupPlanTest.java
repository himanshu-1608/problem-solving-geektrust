package com.example.geektrust.domain_models;

import com.example.geektrust.constants.SubscriptionPlanCategory;
import com.example.geektrust.constants.SubscriptionPlanType;
import com.example.geektrust.constants.TopupPlanType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopupPlanTest {

    @Test
    @DisplayName("getPlanPriceByPlanType should give right value")
    public void shouldProvideExactValues() {
        assertEquals(50.00, TopupPlan.getPlanPriceByPlanType(TopupPlanType.FOUR_DEVICE), 0.0001);
        assertEquals(100.00, TopupPlan.getPlanPriceByPlanType(TopupPlanType.TEN_DEVICE), 0.0001);
    }

    @Test
    @DisplayName("Should instantiate properly")
    void shouldInstantiateProperly() {
        TopupPlan topupPlan = new TopupPlan(TopupPlanType.FOUR_DEVICE, 10);
        assertEquals(topupPlan.getPlanType(), TopupPlanType.FOUR_DEVICE);
        assertEquals(topupPlan.getDurationInMonths(), 10);
    }

}