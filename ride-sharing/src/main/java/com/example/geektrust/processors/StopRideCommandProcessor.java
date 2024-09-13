package com.example.geektrust.processors;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.data_service.driver.DriverDataService;
import com.example.geektrust.data_service.driver.DriverDataServiceImpl;
import com.example.geektrust.data_service.ride.RideDataService;
import com.example.geektrust.data_service.ride.RideDataServiceImpl;
import com.example.geektrust.data_service.rider.RiderDataService;
import com.example.geektrust.data_service.rider.RiderDataServiceImpl;
import com.example.geektrust.domain_models.Ride;
import com.example.geektrust.domain_models.user.Driver;
import com.example.geektrust.domain_models.user.Rider;
import com.example.geektrust.exception.DomainModelConstraintException;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.parsers.argument.StopRideArgument;

public class StopRideCommandProcessor implements ICommandProcessor {

    private final RideDataService rideDataService = RideDataServiceImpl.getInstance();

    private final DriverDataService driverDataService = DriverDataServiceImpl.getInstance();

    private final RiderDataService riderDataService = RiderDataServiceImpl.getInstance();

    @Override
    public void process(CommandArgument commandArgument) throws DomainModelConstraintException {
        StopRideArgument argument = (StopRideArgument) commandArgument;
        Ride ride = rideDataService.getRideById(argument.getRideId()).get();
        ride.completeRide(argument.getDestinationXCoordinate(), argument.getDestinationYCoordinate(), argument.getDuration());
        Driver driver = driverDataService.getDriverById(ride.getDriverId()).get();
        driver.stopDriving();
        Rider rider = riderDataService.getRiderById(ride.getRiderId()).get();
        rider.stopRiding();
        System.out.println(Constants.STOP_RIDE_COMMAND_SUCCESS_MESSAGE + ride.getId());
    }
}
