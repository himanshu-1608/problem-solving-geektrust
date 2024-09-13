package com.example.geektrust.parsers;

import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.CommandArgument;

import java.util.List;

public interface ICommandParser {
    CommandArgument parse(List<String> cliArguments) throws InvalidArgumentException;
}
