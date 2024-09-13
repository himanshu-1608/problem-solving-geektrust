package com.example.geektrust.processors;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.data_service.driver.DriverDataService;
import com.example.geektrust.data_service.driver.DriverDataServiceImpl;
import com.example.geektrust.data_service.rider.RiderDataService;
import com.example.geektrust.data_service.rider.RiderDataServiceImpl;
import com.example.geektrust.data_service.rider_match.RiderMatchDataService;
import com.example.geektrust.data_service.rider_match.RiderMatchDataServiceImpl;
import com.example.geektrust.domain_models.RiderMatch;
import com.example.geektrust.domain_models.user.Driver;
import com.example.geektrust.domain_models.user.Rider;
import com.example.geektrust.domain_models.user.User;
import com.example.geektrust.parsers.argument.CommandArgument;
import com.example.geektrust.parsers.argument.MatchArgument;
import com.example.geektrust.service.MatchService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatchCommandProcessor implements ICommandProcessor {

    private final RiderDataService riderDataService = RiderDataServiceImpl.getInstance();


    private final DriverDataService driverDataService = DriverDataServiceImpl.getInstance();

    private final RiderMatchDataService riderMatchDataService = RiderMatchDataServiceImpl.getInstance();

    @Override
    public void process(CommandArgument commandArgument) {
        MatchArgument argument = (MatchArgument) commandArgument;
        Rider rider = riderDataService.getRiderById(argument.getRiderId()).get();
        List<Driver> matchingDriversForRider = MatchService.getMatchingDriversForRider(rider, driverDataService.getAllAvailableDrivers());
        if(matchingDriversForRider.isEmpty()) {
            System.out.println(Constants.MATCH_COMMAND_NO_DRIVER_ERROR);
            return;
        }
        Optional<RiderMatch> riderMatchOptional = riderMatchDataService.getMatchesForRider(rider.getId());
        RiderMatch riderMatch = riderMatchOptional.orElseGet(() -> new RiderMatch(rider.getId()));
        List<String> matchingDriverIdsForRider = matchingDriversForRider.stream().map(User::getId).collect(Collectors.toList());
        riderMatch.setMatchedDriverIds(matchingDriverIdsForRider);
        riderMatchDataService.addRiderMatch(riderMatch);
        System.out.println(Constants.MATCH_COMMAND_SUCCESS_MESSAGE + String.join(Constants.COMMAND_DELIMITER, matchingDriverIdsForRider));
    }
}
