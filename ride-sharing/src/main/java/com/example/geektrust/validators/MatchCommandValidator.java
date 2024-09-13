package com.example.geektrust.validators;

import com.example.geektrust.data_service.rider.RiderDataService;
import com.example.geektrust.data_service.rider.RiderDataServiceImpl;
import com.example.geektrust.domain_models.user.Rider;
import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.parsers.argument.MatchArgument;

import java.util.Optional;

public class MatchCommandValidator implements ICommandValidator {

    private final RiderDataService riderDataService = RiderDataServiceImpl.getInstance();

    @Override
    public boolean validate(CommandArgument commandArgument) throws InvalidArgumentException {
        MatchArgument argument = (MatchArgument) commandArgument;
        Optional<Rider> riderOptional = riderDataService.getRiderById(argument.getRiderId());
        if(!riderOptional.isPresent()) {
            throw new InvalidArgumentException();
        }
        return true;
    }
}
