package com.example.geektrust;

import com.example.geektrust.command_processor.CommandProcessorFactory;
import com.example.geektrust.command_processor.ICommandProcessor;
import com.example.geektrust.constants.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        run(args);
    }

    private static void run(String[] cliArguments) {
        // the file to be opened for reading
        FileInputStream fis;
        String inputFileLocation = cliArguments[0];
        if(Objects.isNull(inputFileLocation) || inputFileLocation.isEmpty()) {
            throw new RuntimeException("Exception while fetching input file location: " + inputFileLocation);
        }
        try {
            fis = new FileInputStream(inputFileLocation);
            Scanner sc = new Scanner(fis);
            // read line until all lines are executed
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                List<String> commandArguments = Arrays.asList(command.split(Constants.COMMAND_DELIMITER));
                // First word of command will decide the processor to be used, all other arguments to be passed to specific processor
                ICommandProcessor commandProcessor = CommandProcessorFactory
                        .getCommandProcessor(commandArguments.get(Constants.FIRST_ARGUMENT));
                // For all commands, only send the 2nd, 3rd and next arguments
                // Some commands do not even have 2nd argument, so just send empty list
                List<String> subList = (commandArguments.size() >= 2) ?
                        commandArguments.subList(Constants.SECOND_ARGUMENT, commandArguments.size())
                        : new ArrayList<>();
                commandProcessor.process(subList);
            }
            sc.close();
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("Exception while opening file at location: " + inputFileLocation, exception);
        }
    }
}
