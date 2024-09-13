package com.example.geektrust.processors;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.data_service.ride.RideDataService;
import com.example.geektrust.data_service.ride.RideDataServiceImpl;
import com.example.geektrust.domain_models.Ride;
import com.example.geektrust.parsers.argument.BillArgument;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.service.FareCalculationService;
import com.example.geektrust.utils.CalculationUtil;

import java.util.Arrays;
import java.util.List;

public class BillCommandProcessor implements ICommandProcessor {

    private final RideDataService rideDataService = RideDataServiceImpl.getInstance();

    @Override
    public void process(CommandArgument commandArgument) {
        BillArgument argument = (BillArgument) commandArgument;
        Ride ride = rideDataService.getRideById(argument.getRideId()).get();
        double totalFare = FareCalculationService.calculateFareForRide(ride);

        totalFare = CalculationUtil.roundDecimalToScale(totalFare);
        List<String> outputArgs = Arrays.asList(Constants.BILL_COMMAND_SUCCESS_MESSAGE,
                ride.getId(), ride.getDriverId(), CalculationUtil.getRoundedValueForDecimal(totalFare));

        System.out.println(String.join(Constants.COMMAND_DELIMITER, outputArgs));

    }
}
