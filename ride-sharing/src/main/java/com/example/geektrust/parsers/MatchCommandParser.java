package com.example.geektrust.parsers;

import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.parsers.argument.MatchArgument;
import com.example.geektrust.constants.Constants;

import java.util.List;

public class MatchCommandParser implements ICommandParser {
    @Override
    public CommandArgument parse(List<String> cliArguments) throws InvalidArgumentException {
        if(cliArguments.size() != Constants.MATCH_ARGUMENT_SIZE) {
            throw new InvalidArgumentException();
        }
        String riderId = cliArguments.get(Constants.ARGUMENT_1);
        return new MatchArgument(riderId);
    }
}
