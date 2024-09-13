package com.example.geektrust.processors;

import com.example.geektrust.data_service.rider.RiderDataService;
import com.example.geektrust.data_service.rider.RiderDataServiceImpl;
import com.example.geektrust.domain_models.user.Rider;
import com.example.geektrust.parsers.argument.AddRiderArgument;
import com.example.geektrust.parsers.argument.CommandArgument;

public class AddRiderCommandProcessor implements ICommandProcessor {

    private final RiderDataService riderDataService = RiderDataServiceImpl.getInstance();

    @Override
    public void process(CommandArgument commandArgument) {
        AddRiderArgument argument = (AddRiderArgument) commandArgument;
        Rider rider = new Rider(argument.getRiderId(), argument.getRiderXCoordinate(), argument.getRiderYCoordinate());
        riderDataService.addRider(rider);
    }
}
