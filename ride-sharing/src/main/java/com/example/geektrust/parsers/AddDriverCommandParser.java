package com.example.geektrust.parsers;

import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.AddDriverArgument;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.constants.Constants;

import java.util.List;

public class AddDriverCommandParser implements ICommandParser {

    @Override
    public CommandArgument parse(List<String> cliArguments) throws InvalidArgumentException {
        if(cliArguments.size() != Constants.ADD_DRIVER_ARGUMENT_SIZE) {
            throw new InvalidArgumentException();
        }
        String driverId = cliArguments.get(Constants.ARGUMENT_1);
        String driverXCoordinate = cliArguments.get(Constants.ARGUMENT_2);
        String driverYCoordinate = cliArguments.get(Constants.ARGUMENT_3);
        double parsedDriverXCoordinate, parsedDriverYCoordinate;
        try {
            parsedDriverXCoordinate = Double.parseDouble(driverXCoordinate);
            parsedDriverYCoordinate = Double.parseDouble(driverYCoordinate);
        } catch (Exception exception) {
            throw new InvalidArgumentException();
        }
        return new AddDriverArgument(driverId, parsedDriverXCoordinate, parsedDriverYCoordinate);
    }

}
