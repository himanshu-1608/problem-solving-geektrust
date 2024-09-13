package com.example.geektrust.service;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.domain_models.user.Driver;
import com.example.geektrust.domain_models.user.Rider;
import com.example.geektrust.utils.CalculationUtil;

import java.util.List;
import java.util.stream.Collectors;

public class MatchService {

    public static List<Driver> getMatchingDriversForRider(Rider rider, List<Driver> allAvailableDrivers) {
        return allAvailableDrivers.stream()
                .filter(driver -> {
                    double allowedDistance = Constants.MAX_ALLOWED_DISTANCE;
                    double distance = getDistance(rider, driver);
                    return distance <= allowedDistance;
                })
                .sorted((driver1, driver2) -> {
                    double distance1 = getDistance(rider, driver1);
                    double distance2 = getDistance(rider, driver2);
                    if(distance1 == distance2) {
                        return driver1.getId().compareTo(driver2.getId());
                    } else if(distance1 <= distance2) {
                        return Constants.COMPARATOR_LOW;
                    } else {
                        return Constants.COMPARATOR_HIGH;
                    }
                })
                .limit(Constants.MAX_ALLOWED_DRIVERS)
                .collect(Collectors.toList());
    }

    private static double getDistance(Rider rider, Driver driver) {
        return CalculationUtil.getDistance(rider.getLocationXCoordinate(), rider.getLocationYCoordinate(),
                driver.getLocationXCoordinate(), driver.getLocationYCoordinate());
    }
}
