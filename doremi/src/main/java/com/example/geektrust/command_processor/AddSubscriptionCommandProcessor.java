package com.example.geektrust.command_processor;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.constants.ExceptionMessage;
import com.example.geektrust.constants.SubscriptionPlanCategory;
import com.example.geektrust.constants.SubscriptionPlanType;
import com.example.geektrust.domain_models.UserSubscription;
import com.example.geektrust.exception.AddSubscriptionException;

import java.util.List;
import java.util.Objects;

public class AddSubscriptionCommandProcessor implements ICommandProcessor {

    @Override
    public void process(List<String> commandArguments) {

        UserSubscription userSubscription = UserSubscription.getInstance();

        try {

            // check if subscription is present or not
            checkExistingSubscriptionPresent(userSubscription);

            // minimum 2 arguments (subscription category and plan name are required for this command to work)
            checkCommandArgumentSize(commandArguments);

            String inputSubscriptionCategory = commandArguments.get(Constants.FIRST_ARGUMENT);
            String inputSubscriptionPlanType = commandArguments.get(Constants.SECOND_ARGUMENT);

            // check if command line arguments are valid
            checkCommandArgumentsValid(inputSubscriptionCategory, inputSubscriptionPlanType);

            SubscriptionPlanCategory planCategory = SubscriptionPlanCategory.valueOf(inputSubscriptionCategory);
            SubscriptionPlanType planType = SubscriptionPlanType.valueOf(inputSubscriptionPlanType);

            // check if plan already exists for this category
            checkDuplicateSubscriptionPlanForCategory(userSubscription, planCategory);

            userSubscription.addSubscriptionPlan(planCategory, planType);

        } catch (AddSubscriptionException exception) {
            System.out.println(exception.getDisplayMessage());
            userSubscription.setAddSubscriptionException(exception);
        }

    }

    private void checkCommandArgumentSize(List<String> commandArguments) throws AddSubscriptionException {
        if(Objects.isNull(commandArguments)
                || commandArguments.size() < Constants.ADD_SUBSCRIPTION_COMMAND_MIN_ARGUMENTS) {
            throw new AddSubscriptionException(ExceptionMessage.ADD_SUBSCRIPTION_GENERIC_EXCEPTION_MESSAGE +
                    ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION);
        }
    }

    private void checkDuplicateSubscriptionPlanForCategory(UserSubscription userSubscription, SubscriptionPlanCategory planCategory) throws AddSubscriptionException {
        boolean subscriptionPlanPresentForCategory = userSubscription.getSubscriptionPlan().containsKey(planCategory);
        if(subscriptionPlanPresentForCategory) {
            throw new AddSubscriptionException(ExceptionMessage.ADD_SUBSCRIPTION_GENERIC_EXCEPTION_MESSAGE +
                    ExceptionMessage.DUPLICATE_CATEGORY_EXCEPTION);
        }
    }

    private void checkCommandArgumentsValid(String inputSubscriptionCategory, String inputSubscriptionPlanType) throws AddSubscriptionException {
        try {
            SubscriptionPlanCategory.valueOf(inputSubscriptionCategory);
            SubscriptionPlanType.valueOf(inputSubscriptionPlanType);
        } catch (IllegalArgumentException exception) {
            throw new AddSubscriptionException(ExceptionMessage.ADD_SUBSCRIPTION_GENERIC_EXCEPTION_MESSAGE +
                    ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION);
        }
    }

    private void checkExistingSubscriptionPresent(UserSubscription userSubscription) throws AddSubscriptionException {
        boolean isExistingSubscriptionPresent = Objects.nonNull(userSubscription.getSubscriptionStartDate());
        if(!isExistingSubscriptionPresent) {
            throw new AddSubscriptionException(ExceptionMessage.ADD_SUBSCRIPTION_GENERIC_EXCEPTION_MESSAGE +
                    userSubscription.getStartSubscriptionExceptionMessage());
        }
    }

}
