package com.example.geektrust.domain_models;

import com.example.geektrust.constants.SubscriptionPlanCategory;
import com.example.geektrust.constants.SubscriptionPlanType;

public class SubscriptionPlan {

    private final SubscriptionPlanCategory planCategory;

    private final SubscriptionPlanType planType;

    public SubscriptionPlan(SubscriptionPlanCategory planCategory, SubscriptionPlanType planType) {
        this.planCategory = planCategory;
        this.planType = planType;
    }

    public static int getDurationForPlanType(SubscriptionPlanType planType) {
        switch (planType) {
            case FREE:
            case PERSONAL: return 1;
            case PREMIUM:
            default: return 3;
        }
    }

    public static Double getPriceForPlanCategoryAndPlanType(SubscriptionPlanCategory planCategory, SubscriptionPlanType planType) {
        switch (planCategory) {
            case MUSIC:
                return getPriceForPlanTypeInMusicCategory(planType);
            case VIDEO:
                return getPriceForPlanTypeInVideoCategory(planType);
            case PODCAST:
            default:
                return getPriceForPlanTypeInPodcastCategory(planType);
        }
    }

    private static Double getPriceForPlanTypeInMusicCategory(SubscriptionPlanType planType) {
        switch (planType) {
            case FREE: return 0.00;
            case PERSONAL: return 100.00;
            case PREMIUM:
            default: return 250.00;
        }
    }

    private static Double getPriceForPlanTypeInVideoCategory(SubscriptionPlanType planType) {
        switch (planType) {
            case FREE: return 0.00;
            case PERSONAL: return 200.00;
            case PREMIUM:
            default: return 500.00;
        }
    }

    private static Double getPriceForPlanTypeInPodcastCategory(SubscriptionPlanType planType) {
        switch (planType) {
            case FREE: return 0.00;
            case PERSONAL: return 100.00;
            case PREMIUM:
            default: return 300.00;
        }
    }

    public SubscriptionPlanType getPlanType() {
        return planType;
    }

    public SubscriptionPlanCategory getPlanCategory() {
        return planCategory;
    }
}