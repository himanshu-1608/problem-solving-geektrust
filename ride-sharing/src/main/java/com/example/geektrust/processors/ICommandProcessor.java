package com.example.geektrust.processors;

import com.example.geektrust.exception.DomainModelConstraintException;
import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.CommandArgument;

public interface ICommandProcessor {
    void process(CommandArgument commandArgument) throws InvalidArgumentException, DomainModelConstraintException;
}
