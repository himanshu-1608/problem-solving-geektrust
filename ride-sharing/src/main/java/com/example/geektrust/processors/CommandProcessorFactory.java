package com.example.geektrust.processors;

import com.example.geektrust.constants.CommandType;
import com.example.geektrust.exception.InvalidArgumentException;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessorFactory {

    private static final Map<CommandType, ICommandProcessor> commandTypeToProcessorMap = new HashMap<>();

    static {
        commandTypeToProcessorMap.put(CommandType.ADD_DRIVER, new AddDriverCommandProcessor());
        commandTypeToProcessorMap.put(CommandType.ADD_RIDER, new AddRiderCommandProcessor());
        commandTypeToProcessorMap.put(CommandType.MATCH, new MatchCommandProcessor());
        commandTypeToProcessorMap.put(CommandType.START_RIDE, new StartRideCommandProcessor());
        commandTypeToProcessorMap.put(CommandType.STOP_RIDE, new StopRideCommandProcessor());
        commandTypeToProcessorMap.put(CommandType.BILL, new BillCommandProcessor());
    }

    public static ICommandProcessor getCommandProcessor(String commandTypeString) throws InvalidArgumentException {
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandTypeString);
        } catch (Exception exception) {
            throw new InvalidArgumentException();
        }
        return commandTypeToProcessorMap.get(commandType);
    }

}
