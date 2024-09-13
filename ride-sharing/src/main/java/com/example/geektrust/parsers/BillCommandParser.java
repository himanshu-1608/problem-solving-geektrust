package com.example.geektrust.parsers;

import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.BillArgument;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.constants.Constants;

import java.util.List;

public class BillCommandParser implements ICommandParser {
    @Override
    public CommandArgument parse(List<String> cliArguments) throws InvalidArgumentException {
        if(cliArguments.size() != Constants.BILL_ARGUMENT_SIZE) {
            throw new InvalidArgumentException();
        }
        String rideId = cliArguments.get(Constants.ARGUMENT_1);
        return new BillArgument(rideId);
    }
}
