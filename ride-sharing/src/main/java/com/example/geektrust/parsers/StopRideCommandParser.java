package com.example.geektrust.parsers;

import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.parsers.argument.StopRideArgument;
import com.example.geektrust.constants.Constants;

import java.util.List;

public class StopRideCommandParser implements ICommandParser {

    @Override
    public CommandArgument parse(List<String> cliArguments) throws InvalidArgumentException {
        if(cliArguments.size() != Constants.STOP_RIDE_ARGUMENT_SIZE) {
            throw new InvalidArgumentException();
        }
        String rideId = cliArguments.get(Constants.ARGUMENT_1);
        String rideDestinationXCoordinate = cliArguments.get(Constants.ARGUMENT_2);
        String rideDestinationYCoordinate = cliArguments.get(Constants.ARGUMENT_3);
        String rideDuration = cliArguments.get(Constants.ARGUMENT_4);
        double parsedRideDestinationXCoordinate, parsedRideDestinationYCoordinate;
        long parsedRideDuration;
        try {
            parsedRideDestinationXCoordinate = Double.parseDouble(rideDestinationXCoordinate);
            parsedRideDestinationYCoordinate = Double.parseDouble(rideDestinationYCoordinate);
            parsedRideDuration = Long.parseLong(rideDuration);
        } catch (Exception exception) {
            throw new InvalidArgumentException();
        }
        return new StopRideArgument(rideId, parsedRideDestinationXCoordinate, parsedRideDestinationYCoordinate, parsedRideDuration);
    }
}
