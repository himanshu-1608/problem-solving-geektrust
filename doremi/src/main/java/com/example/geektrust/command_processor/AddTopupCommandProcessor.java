package com.example.geektrust.command_processor;

import com.example.geektrust.constants.*;
import com.example.geektrust.domain_models.UserSubscription;
import com.example.geektrust.exception.AddTopupException;

import java.util.List;
import java.util.Objects;

public class AddTopupCommandProcessor implements ICommandProcessor {

    @Override
    public void process(List<String> commandArguments) {

        UserSubscription userSubscription = UserSubscription.getInstance();

        try {

            // check if subscription is present or not
            checkExistingSubscriptionPresent(userSubscription);

            // minimum 2 argument is required
            checkCommandArgumentSize(commandArguments);

            String inputTopupPlanType = commandArguments.get(Constants.FIRST_ARGUMENT);
            String inputTopupDuration = commandArguments.get(Constants.SECOND_ARGUMENT);

            // check if command line arguments are valid
            checkInputPlanTypeValid(inputTopupPlanType);
            checkInputTopupDuration(inputTopupDuration);
            TopupPlanType planType = TopupPlanType.valueOf(inputTopupPlanType);
            int durationInMonths = Integer.parseInt(inputTopupDuration);

            checkAnySubscriptionPlanPresent(userSubscription);

            checkIfTopupPlanAlreadyExists(userSubscription);

            userSubscription.addTopupPlan(planType, durationInMonths);

        } catch (AddTopupException exception) {
            System.out.println(exception.getDisplayMessage());
            userSubscription.setAddTopupException(exception);
        }

    }

    private void checkInputTopupDuration(String inputTopupDuration) throws AddTopupException {
        try {
            Integer.parseInt(inputTopupDuration);
        } catch (NumberFormatException exception) {
            throw new AddTopupException(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE +
                    ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION);
        }
    }

    private void checkAnySubscriptionPlanPresent(UserSubscription userSubscription) throws AddTopupException {
        if(userSubscription.getSubscriptionPlan().isEmpty()) {
            throw new AddTopupException(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE +
                    ExceptionMessage.SUBSCRIPTION_NOT_FOUND_EXCEPTION);
        }
    }

    private void checkIfTopupPlanAlreadyExists(UserSubscription userSubscription) throws AddTopupException {
        if(Objects.nonNull(userSubscription.getTopupPlan())) {
            throw new AddTopupException(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE +
                    ExceptionMessage.DUPLICATE_TOPUP_EXCEPTION);
        }
    }

    private void checkInputPlanTypeValid(String inputTopupPlanType) throws AddTopupException {
        try {
            TopupPlanType.valueOf(inputTopupPlanType);
        } catch (IllegalArgumentException exception) {
            throw new AddTopupException(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE +
                    ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION);
        }
    }

    private void checkCommandArgumentSize(List<String> commandArguments) throws AddTopupException {
        if(Objects.isNull(commandArguments) || commandArguments.size() < 2) {
            throw new AddTopupException(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE
                    + ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION);
        }
    }

    private void checkExistingSubscriptionPresent(UserSubscription userSubscription) throws AddTopupException {
        boolean isExistingSubscriptionPresent = Objects.nonNull(userSubscription.getSubscriptionStartDate());
        if(!isExistingSubscriptionPresent) {
            throw new AddTopupException(ExceptionMessage.ADD_TOPUP_GENERIC_EXCEPTION_MESSAGE +
                    userSubscription.getStartSubscriptionExceptionMessage());
        }
    }

}
