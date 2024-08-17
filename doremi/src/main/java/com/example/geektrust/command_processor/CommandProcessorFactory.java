package com.example.geektrust.command_processor;

import com.example.geektrust.constants.CommandType;

import java.util.EnumMap;

public class CommandProcessorFactory {

    private static final EnumMap<CommandType, ICommandProcessor> commandTypeToProcessorMap = new EnumMap<>(CommandType.class);

    private CommandProcessorFactory() {}

    public static ICommandProcessor getCommandProcessor(String commandType) {
        CommandType commandTypeEnum;
        try {
            commandTypeEnum = CommandType.valueOf(commandType);
        } catch (IllegalArgumentException exception) {
            throw new RuntimeException("Illegal Command Type: " + commandType, exception);
        }
        if(!commandTypeToProcessorMap.containsKey(commandTypeEnum)) {
            commandTypeToProcessorMap.put(commandTypeEnum, getCommandProcessorForCommandType(commandTypeEnum));
        }
        return commandTypeToProcessorMap.get(commandTypeEnum);
    }

    private static ICommandProcessor getCommandProcessorForCommandType(CommandType commandType) {
        switch (commandType) {
            case START_SUBSCRIPTION:
                return new StartSubscriptionCommandProcessor();
            case ADD_SUBSCRIPTION:
                return new AddSubscriptionCommandProcessor();
            case ADD_TOPUP:
                return new AddTopupCommandProcessor();
            case PRINT_RENEWAL_DETAILS:
            default:
                return new PrintRenewalDetailsCommandProcessor();
        }
    }

}
