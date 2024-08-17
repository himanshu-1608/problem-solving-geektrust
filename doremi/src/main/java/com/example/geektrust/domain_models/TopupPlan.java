package com.example.geektrust.domain_models;

import com.example.geektrust.constants.TopupPlanType;

public class TopupPlan {

    private final TopupPlanType planType;

    private final int durationInMonths;

    public TopupPlan(TopupPlanType planType, int topupDuration) {
        this.planType = planType;
        this.durationInMonths = topupDuration;
    }

    public static Double getPlanPriceByPlanType(TopupPlanType planType) {
        switch (planType) {
            case FOUR_DEVICE:
                return 50.00;
            case TEN_DEVICE:
            default:
                return 100.00;
        }
    }

    public TopupPlanType getPlanType() {
        return planType;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }
}