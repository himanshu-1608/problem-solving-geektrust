package com.example.geektrust.domain_models;

import com.example.geektrust.constants.SubscriptionPlanCategory;
import com.example.geektrust.constants.SubscriptionPlanType;
import com.example.geektrust.constants.TopupPlanType;
import com.example.geektrust.exception.AddSubscriptionException;
import com.example.geektrust.exception.AddTopupException;
import com.example.geektrust.exception.PrintRenewalDetailsException;
import com.example.geektrust.exception.StartSubscriptionException;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class UserSubscription {

    private final EnumMap<SubscriptionPlanCategory, SubscriptionPlan> subscriptionPlan = new EnumMap<>(SubscriptionPlanCategory.class);

    private Date subscriptionStartDate;

    private StartSubscriptionException startSubscriptionException;

    private AddSubscriptionException addSubscriptionException;

    private AddTopupException addTopupException;

    private PrintRenewalDetailsException printRenewalDetailsException;

    private TopupPlan topupPlan;

    private static UserSubscription instance;

    private UserSubscription() {}

    public static UserSubscription getInstance() {
        if(Objects.isNull(instance)) {
            instance = new UserSubscription();
        }
        return instance;
    }

    public void setStartSubscriptionException(StartSubscriptionException startSubscriptionException) {
        this.startSubscriptionException = startSubscriptionException;
    }

    public void setPrintRenewalDetailsException(PrintRenewalDetailsException printRenewalDetailsException) {
        this.printRenewalDetailsException = printRenewalDetailsException;
    }

    public void setAddSubscriptionException(AddSubscriptionException addSubscriptionException) {
        this.addSubscriptionException = addSubscriptionException;
    }

    public void setAddTopupException(AddTopupException addTopupException) {
        this.addTopupException = addTopupException;
    }

    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public Map<SubscriptionPlanCategory, SubscriptionPlan> getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public String getStartSubscriptionExceptionMessage() {
        if(Objects.isNull(this.startSubscriptionException)) {
            return null;
        }
        return this.startSubscriptionException.getDisplayMessage();
    }

    public String getAddSubscriptionExceptionMessage() {
        if(Objects.isNull(this.addSubscriptionException)) {
            return null;
        }
        return this.addSubscriptionException.getDisplayMessage();
    }

    public String getAddTopupExceptionMessage() {
        if(Objects.isNull(this.addTopupException)) {
            return null;
        }
        return this.addTopupException.getDisplayMessage();
    }

    public String getPrintRenewalDetailsException() {
        if(Objects.isNull(this.printRenewalDetailsException)) {
            return null;
        }
        return this.printRenewalDetailsException.getDisplayMessage();
    }

    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void addSubscriptionPlan(SubscriptionPlanCategory planCategory, SubscriptionPlanType planType) {
        SubscriptionPlan plan = new SubscriptionPlan(planCategory, planType);
        this.subscriptionPlan.put(planCategory, plan);
    }

    public TopupPlan getTopupPlan() {
        return this.topupPlan;
    }

    public void addTopupPlan(TopupPlanType planType, int topupDuration) {
        this.topupPlan = new TopupPlan(planType, topupDuration);
    }
}
