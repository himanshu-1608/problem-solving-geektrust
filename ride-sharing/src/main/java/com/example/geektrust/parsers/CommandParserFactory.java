package com.example.geektrust.parsers;

import com.example.geektrust.constants.CommandType;
import com.example.geektrust.exception.InvalidArgumentException;

import java.util.HashMap;
import java.util.Map;

public final class CommandParserFactory {

    private static final Map<CommandType, ICommandParser> commandTypeToParserMap = new HashMap<>();

    static {
        commandTypeToParserMap.put(CommandType.ADD_DRIVER, new AddDriverCommandParser());
        commandTypeToParserMap.put(CommandType.ADD_RIDER, new AddRiderCommandParser());
        commandTypeToParserMap.put(CommandType.MATCH, new MatchCommandParser());
        commandTypeToParserMap.put(CommandType.START_RIDE, new StartRideCommandParser());
        commandTypeToParserMap.put(CommandType.STOP_RIDE, new StopRideCommandParser());
        commandTypeToParserMap.put(CommandType.BILL, new BillCommandParser());
    }

    private CommandParserFactory() {};

    public static ICommandParser getCommandParser(String commandTypeString) throws InvalidArgumentException {
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandTypeString);
        } catch (Exception exception) {
            throw new InvalidArgumentException();
        }
        return commandTypeToParserMap.get(commandType);
    }
}
