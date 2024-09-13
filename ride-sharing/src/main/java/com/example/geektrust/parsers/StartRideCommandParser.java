package com.example.geektrust.parsers;

import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.parsers.argument.StartRideArgument;
import com.example.geektrust.constants.Constants;

import java.util.List;

public class StartRideCommandParser implements ICommandParser {
    @Override
    public CommandArgument parse(List<String> cliArguments) throws InvalidArgumentException {
        if(cliArguments.size() != Constants.START_RIDE_ARGUMENT_SIZE) {
            throw new InvalidArgumentException();
        }
        String rideId = cliArguments.get(Constants.ARGUMENT_1);
        String driverIndex = cliArguments.get(Constants.ARGUMENT_2);
        String riderId = cliArguments.get(Constants.ARGUMENT_3);
        int parsedDriverIndex;
        try {
            parsedDriverIndex = Integer.parseInt(driverIndex);
        } catch (Exception exception) {
            throw new InvalidArgumentException();
        }
        return new StartRideArgument(rideId, parsedDriverIndex - Constants.STANDARD_REDUCE_INDEX, riderId);
    }
}
