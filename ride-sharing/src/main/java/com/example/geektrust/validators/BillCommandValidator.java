package com.example.geektrust.validators;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.constants.RideStatus;
import com.example.geektrust.data_service.ride.RideDataService;
import com.example.geektrust.data_service.ride.RideDataServiceImpl;
import com.example.geektrust.domain_models.Ride;
import com.example.geektrust.parsers.argument.BillArgument;
import com.example.geektrust.parsers.argument.CommandArgument;

import java.util.Optional;

public class BillCommandValidator implements ICommandValidator {

    private final RideDataService rideDataService = RideDataServiceImpl.getInstance();

    @Override
    public boolean validate(CommandArgument commandArgument) {
        BillArgument argument = (BillArgument) commandArgument;
        Optional<Ride> rideOptional = rideDataService.getRideById(argument.getRideId());
        if(!rideOptional.isPresent()) {
            System.out.println(Constants.INVALID_RIDE_MESSAGE);
            return false;
        }
        Ride ride = rideOptional.get();
        if(!RideStatus.COMPLETED.equals(ride.getStatus())) {
            System.out.println(Constants.RIDE_NOT_COMPLETED_MESSAGE);
            return false;
        }
        return true;
    }
}
