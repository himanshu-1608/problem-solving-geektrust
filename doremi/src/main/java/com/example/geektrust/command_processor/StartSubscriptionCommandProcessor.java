package com.example.geektrust.command_processor;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.constants.ExceptionMessage;
import com.example.geektrust.domain_models.UserSubscription;
import com.example.geektrust.exception.StartSubscriptionException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class StartSubscriptionCommandProcessor implements ICommandProcessor {

    @Override
    public void process(List<String> commandArguments) {

        UserSubscription userSubscription = UserSubscription.getInstance();

        try {
            checkCommandArgumentSize(commandArguments);

            String inputSubscriptionStartDate = commandArguments.get(Constants.FIRST_ARGUMENT);
            SimpleDateFormat inputDateFormatter = new SimpleDateFormat(Constants.STANDARD_DATE_FORMAT);

            Date parsedSubscriptionStartDate = checkAndReturnSubscriptionStartDateArgument(inputDateFormatter, inputSubscriptionStartDate);

            // if the date was valid, update the subscription start date in singleton instance
            userSubscription.setSubscriptionStartDate(parsedSubscriptionStartDate);

        } catch (StartSubscriptionException exception) {
            System.out.println(exception.getDisplayMessage());
            userSubscription.setStartSubscriptionException(exception);
        }

    }

    private Date checkAndReturnSubscriptionStartDateArgument(SimpleDateFormat inputDateFormatter,
                                                             String inputSubscriptionStartDate) throws StartSubscriptionException {
        try {
            inputDateFormatter.setLenient(false);
            return inputDateFormatter.parse(inputSubscriptionStartDate);
        } catch (ParseException parseException) {
            // For invalid date, add exception in subscription class for showing it in later commands
            throw new StartSubscriptionException(ExceptionMessage.INVALID_DATE_EXCEPTION);
        }
    }

    private void checkCommandArgumentSize(List<String> commandArguments) throws StartSubscriptionException {
        if(Objects.isNull(commandArguments) || commandArguments.isEmpty()) {
            // if there are no arguments present
            throw new StartSubscriptionException(ExceptionMessage.INVALID_DATE_EXCEPTION);
        }
    }

}
