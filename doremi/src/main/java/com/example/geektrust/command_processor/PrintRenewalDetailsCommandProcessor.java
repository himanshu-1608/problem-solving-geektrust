package com.example.geektrust.command_processor;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.constants.ExceptionMessage;
import com.example.geektrust.constants.SubscriptionPlanCategory;
import com.example.geektrust.domain_models.SubscriptionPlan;
import com.example.geektrust.domain_models.TopupPlan;
import com.example.geektrust.domain_models.UserSubscription;
import com.example.geektrust.exception.PrintRenewalDetailsException;

import java.text.SimpleDateFormat;
import java.util.*;

public class PrintRenewalDetailsCommandProcessor implements ICommandProcessor {

    @Override
    public void process(List<String> commandArguments) {

        UserSubscription userSubscription = UserSubscription.getInstance();

        try {

            // check if subscription is present or not
            checkExistingSubscriptionPresent(userSubscription);
            checkExistingSubscriptionPlan(userSubscription);

            SimpleDateFormat outputDateFormatter = new SimpleDateFormat(Constants.STANDARD_DATE_FORMAT);

            Double totalRenewalAmount = 0.00;
            for(Map.Entry<SubscriptionPlanCategory, SubscriptionPlan> entrySet : userSubscription.getSubscriptionPlan().entrySet()) {
                SubscriptionPlanCategory category = entrySet.getKey();
                SubscriptionPlan subscriptionPlan = entrySet.getValue();

                Date renewalReminderDate = getRenewalReminderDate(userSubscription.getSubscriptionStartDate(), subscriptionPlan);
                totalRenewalAmount += getPriceForSubscriptionPlan(subscriptionPlan);

                System.out.println(String.join(" ", Constants.RENEWAL_REMINDER_OUTPUT,
                        category.toString(), outputDateFormatter.format(renewalReminderDate)));
            }
            if(Objects.nonNull(userSubscription.getTopupPlan())) {
                totalRenewalAmount += getTotalTopupPrice(userSubscription);
            }

            System.out.println(String.join(" ", Constants.RENEWAL_AMOUNT_OUTPUT, String.valueOf(totalRenewalAmount.intValue())));

        } catch (PrintRenewalDetailsException exception) {
            System.out.println(exception.getDisplayMessage());
            userSubscription.setPrintRenewalDetailsException(exception);
        }

    }

    private Double getTotalTopupPrice(UserSubscription userSubscription) {
        return TopupPlan.getPlanPriceByPlanType(userSubscription.getTopupPlan().getPlanType())
                * userSubscription.getTopupPlan().getDurationInMonths();
    }

    private Double getPriceForSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        return SubscriptionPlan.getPriceForPlanCategoryAndPlanType(
                subscriptionPlan.getPlanCategory(), subscriptionPlan.getPlanType());
    }

    private Date getRenewalReminderDate(Date subscriptionStartDate, SubscriptionPlan subscriptionPlan) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(subscriptionStartDate);
        calendar.add(Calendar.MONTH, SubscriptionPlan.getDurationForPlanType(subscriptionPlan.getPlanType()));
        calendar.add(Calendar.DATE, -10);
        return calendar.getTime();
    }

    private void checkExistingSubscriptionPlan(UserSubscription userSubscription) throws PrintRenewalDetailsException {
        if(userSubscription.getSubscriptionPlan().isEmpty()) {
            throw new PrintRenewalDetailsException(ExceptionMessage.SUBSCRIPTION_NOT_FOUND_EXCEPTION);
        }
    }

    private void checkExistingSubscriptionPresent(UserSubscription userSubscription) throws PrintRenewalDetailsException {
        boolean isExistingSubscriptionPresent = Objects.nonNull(userSubscription.getSubscriptionStartDate());
        if(!isExistingSubscriptionPresent) {
            throw new PrintRenewalDetailsException(ExceptionMessage.SUBSCRIPTION_NOT_FOUND_EXCEPTION);
        }
    }
}
