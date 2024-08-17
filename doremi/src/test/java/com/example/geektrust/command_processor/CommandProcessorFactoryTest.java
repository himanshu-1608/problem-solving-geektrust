package com.example.geektrust.command_processor;

import com.example.geektrust.constants.CommandType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandProcessorFactoryTest {

    @Test
    @DisplayName("Start Subscription Command Processor")
    void shouldProvideStartSubscriptionCommandProcessor() {
        ICommandProcessor commandProcessor = CommandProcessorFactory.getCommandProcessor(CommandType.START_SUBSCRIPTION.toString());
        assertTrue(commandProcessor instanceof StartSubscriptionCommandProcessor);
    }

    @Test
    @DisplayName("Start Subscription Command Processor Fetched Twice")
    void shouldProvideStartSubscriptionCommandProcessorFromMapSecondTime() {
        ICommandProcessor commandProcessor = CommandProcessorFactory.getCommandProcessor(CommandType.START_SUBSCRIPTION.toString());
        assertTrue(commandProcessor instanceof StartSubscriptionCommandProcessor);
        ICommandProcessor commandProcessor2 = CommandProcessorFactory.getCommandProcessor(CommandType.START_SUBSCRIPTION.toString());
        assertTrue(commandProcessor2 instanceof StartSubscriptionCommandProcessor);
    }

    @Test
    @DisplayName("Add Subscription Command Processor")
    void shouldProvideAddSubscriptionCommandProcessor() {
        ICommandProcessor commandProcessor = CommandProcessorFactory.getCommandProcessor(CommandType.ADD_SUBSCRIPTION.toString());
        assertTrue(commandProcessor instanceof AddSubscriptionCommandProcessor);
    }

    @Test
    @DisplayName("Add Topup Command Processor")
    void shouldProvideAddTopupCommandProcessor() {
        ICommandProcessor commandProcessor = CommandProcessorFactory.getCommandProcessor(CommandType.ADD_TOPUP.toString());
        assertTrue(commandProcessor instanceof AddTopupCommandProcessor);
    }

    @Test
    @DisplayName("Print Renewal Details Command Processor")
    void shouldProvidePrintRenewalDetailsCommandProcessor() {
        ICommandProcessor commandProcessor = CommandProcessorFactory.getCommandProcessor(CommandType.PRINT_RENEWAL_DETAILS.toString());
        assertTrue(commandProcessor instanceof PrintRenewalDetailsCommandProcessor);
    }

    @Test
    @DisplayName("Invalid Command")
    void shouldThrowErrorForInvalidCommand() {
        String commandType = "Hello World";
        assertThrows(RuntimeException.class, () -> {
            CommandProcessorFactory.getCommandProcessor(commandType);
        }, "Illegal Command Type: " + commandType);
    }
}