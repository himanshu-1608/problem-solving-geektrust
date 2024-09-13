package com.example.geektrust.processors;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.constants.DrivingStatus;
import com.example.geektrust.data_service.driver.DriverDataService;
import com.example.geektrust.data_service.driver.DriverDataServiceImpl;
import com.example.geektrust.data_service.ride.RideDataService;
import com.example.geektrust.data_service.ride.RideDataServiceImpl;
import com.example.geektrust.data_service.rider.RiderDataService;
import com.example.geektrust.data_service.rider.RiderDataServiceImpl;
import com.example.geektrust.data_service.rider_match.RiderMatchDataService;
import com.example.geektrust.data_service.rider_match.RiderMatchDataServiceImpl;
import com.example.geektrust.domain_models.Ride;
import com.example.geektrust.domain_models.RiderMatch;
import com.example.geektrust.domain_models.user.Driver;
import com.example.geektrust.domain_models.user.Rider;
import com.example.geektrust.exception.DomainModelConstraintException;
import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.parsers.argument.StartRideArgument;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StartRideCommandProcessor implements ICommandProcessor {

    private final RiderDataService riderDataService = RiderDataServiceImpl.getInstance();

    private final RiderMatchDataService riderMatchDataService = RiderMatchDataServiceImpl.getInstance();

    private final RideDataService rideDataService = RideDataServiceImpl.getInstance();

    private final DriverDataService driverDataService = DriverDataServiceImpl.getInstance();

    @Override
    public void process(CommandArgument commandArgument) throws DomainModelConstraintException {
        StartRideArgument argument = (StartRideArgument) commandArgument;
        Rider rider = riderDataService.getRiderById(argument.getRiderId()).get();
        List<String> matchedDriverIds = riderMatchDataService.getMatchesForRider(rider.getId()).get().getMatchedDriverIds();
        Driver matchedDriver = driverDataService.getDriverById(matchedDriverIds.get(argument.getDriverIndex())).get();
        Ride ride = new Ride(argument.getRideId(), argument.getRiderId(),
                matchedDriver.getId(), rider.getLocationXCoordinate(), rider.getLocationYCoordinate());
        rideDataService.addRide(ride);
        matchedDriver.startDriving();
        rider.startRiding();
        System.out.println(Constants.START_RIDE_COMMAND_SUCCESS_MESSAGE + ride.getId());
    }
}
