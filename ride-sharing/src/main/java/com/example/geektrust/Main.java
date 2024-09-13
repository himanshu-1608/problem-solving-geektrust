package com.example.geektrust;

import com.example.geektrust.exception.DomainModelConstraintException;
import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.parsers.CommandParserFactory;
import com.example.geektrust.parsers.ICommandParser;
import com.example.geektrust.constants.Constants;
import com.example.geektrust.processors.CommandProcessorFactory;
import com.example.geektrust.processors.ICommandProcessor;
import com.example.geektrust.validators.CommandValidatorFactory;
import com.example.geektrust.validators.ICommandValidator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InvalidArgumentException, DomainModelConstraintException {
        run(args);
    }

    private static void run(String[] cliArguments) throws InvalidArgumentException, DomainModelConstraintException {
        // the file to be opened for reading
        FileInputStream fis;
        String inputFileLocation = cliArguments[0];
        if(Objects.isNull(inputFileLocation) || inputFileLocation.isEmpty()) {
            throw new InvalidArgumentException();
        }
        try {
            fis = new FileInputStream(inputFileLocation);
            Scanner sc = new Scanner(fis);
            // read line until all lines are executed
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                List<String> commandArguments = Arrays.asList(command.split(Constants.COMMAND_DELIMITER));
                // First word of command will decide the parser to be used
                ICommandParser commandParser = CommandParserFactory
                        .getCommandParser(commandArguments.get(Constants.ARGUMENT_0));
                CommandArgument commandArgument = commandParser.parse(commandArguments);
                // Validate the arguments and logic before processing them
                ICommandValidator commandValidator = CommandValidatorFactory.getCommandValidator(commandArguments.get(Constants.ARGUMENT_0));
                boolean isValidCommand = commandValidator.validate(commandArgument);
                if(isValidCommand) {
                    ICommandProcessor commandProcessor = CommandProcessorFactory.getCommandProcessor(commandArguments.get(Constants.ARGUMENT_0));
                    commandProcessor.process(commandArgument);
                }
            }
            sc.close();
        } catch (FileNotFoundException exception) {
            throw new InvalidArgumentException();
        }
    }

}
