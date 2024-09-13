package com.example.geektrust.validators;

import com.example.geektrust.constants.CommandType;
import com.example.geektrust.exception.InvalidArgumentException;

import java.util.HashMap;
import java.util.Map;

public class CommandValidatorFactory {

    private static final Map<CommandType, ICommandValidator> commandTypeToValidatorMap = new HashMap<>();

    static {
        commandTypeToValidatorMap.put(CommandType.ADD_DRIVER, new AddDriverCommandValidator());
        commandTypeToValidatorMap.put(CommandType.ADD_RIDER, new AddRiderCommandValidator());
        commandTypeToValidatorMap.put(CommandType.MATCH, new MatchCommandValidator());
        commandTypeToValidatorMap.put(CommandType.START_RIDE, new StartRideCommandValidator());
        commandTypeToValidatorMap.put(CommandType.STOP_RIDE, new StopRideCommandValidator());
        commandTypeToValidatorMap.put(CommandType.BILL, new BillCommandValidator());
    }

    public static ICommandValidator getCommandValidator(String commandTypeString) throws InvalidArgumentException {
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandTypeString);
        } catch (Exception exception) {
            throw new InvalidArgumentException();
        }
        return commandTypeToValidatorMap.get(commandType);
    }
}
