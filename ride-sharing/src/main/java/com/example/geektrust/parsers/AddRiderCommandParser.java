package com.example.geektrust.parsers;

import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.AddRiderArgument;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.constants.Constants;

import java.util.List;

public class AddRiderCommandParser implements ICommandParser {
    @Override
    public CommandArgument parse(List<String> cliArguments) throws InvalidArgumentException {
        if(cliArguments.size() != Constants.ADD_RIDER_ARGUMENT_SIZE) {
            throw new InvalidArgumentException();
        }
        String riderId = cliArguments.get(Constants.ARGUMENT_1);
        String riderXCoordinate = cliArguments.get(Constants.ARGUMENT_2);
        String riderYCoordinate = cliArguments.get(Constants.ARGUMENT_3);
        double parsedRiderXCoordinate, parsedRiderYCoordinate;
        try {
            parsedRiderXCoordinate = Double.parseDouble(riderXCoordinate);
            parsedRiderYCoordinate = Double.parseDouble(riderYCoordinate);
        } catch (Exception exception) {
            throw new InvalidArgumentException();
        }
        return new AddRiderArgument(riderId, parsedRiderXCoordinate, parsedRiderYCoordinate);
    }
}
