package com.example.geektrust.validators;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.constants.DrivingStatus;
import com.example.geektrust.constants.RidingStatus;
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
import com.example.geektrust.exception.InvalidArgumentException;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.parsers.argument.StartRideArgument;

import java.util.List;
import java.util.Optional;

public class StartRideCommandValidator implements ICommandValidator {

    private final RiderDataService riderDataService = RiderDataServiceImpl.getInstance();

    private final RiderMatchDataService riderMatchDataService = RiderMatchDataServiceImpl.getInstance();

    private final RideDataService rideDataService = RideDataServiceImpl.getInstance();

    private final DriverDataService driverDataService = DriverDataServiceImpl.getInstance();

    @Override
    public boolean validate(CommandArgument commandArgument) throws InvalidArgumentException {
        StartRideArgument argument = (StartRideArgument) commandArgument;
        Optional<Ride> rideOptional = rideDataService.getRideById(argument.getRideId());
        if(rideOptional.isPresent()) {
            System.out.println(Constants.INVALID_RIDE_MESSAGE);
            return false;
        }
        Optional<Rider> riderOptional = riderDataService.getRiderById(argument.getRiderId());
        if(!riderOptional.isPresent()) {
            throw new InvalidArgumentException();
        }
        Rider rider = riderOptional.get();
        if(!RidingStatus.IDLE.equals(rider.getRidingStatus())) {
            System.out.println(Constants.INVALID_RIDE_MESSAGE);
            return false;
        }
        Optional<RiderMatch> matchesForRiderOptional = riderMatchDataService.getMatchesForRider(rider.getId());
        if(!matchesForRiderOptional.isPresent()) {
            System.out.println(Constants.INVALID_RIDE_MESSAGE);
            return false;
        }
        List<String> matchedDriverIds = matchesForRiderOptional.get().getMatchedDriverIds();
        if(matchedDriverIds.size() < argument.getDriverIndex() + 1) {
            System.out.println(Constants.INVALID_RIDE_MESSAGE);
            return false;
        }
        String driverId = matchedDriverIds.get(argument.getDriverIndex());
        Driver matchedDriver = driverDataService.getDriverById(driverId).get();
        if(!DrivingStatus.AVAILABLE.equals(matchedDriver.getDrivingStatus())) {
            System.out.println(Constants.INVALID_RIDE_MESSAGE);
            return false;
        }
        return true;
    }
}
