package com.example.geektrust.service;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.domain_models.Ride;
import com.example.geektrust.utils.CalculationUtil;

public class FareCalculationService {

    public static double calculateFareForRide(Ride ride) {
        double rideDistance = CalculationUtil.getDistance(ride.getSourceXCoordinate(),
                ride.getSourceYCoordinate(), ride.getDestinationXCoordinate(), ride.getDestinationYCoordinate());

        double totalFare = Constants.START_FARE;

        totalFare += getBasePrice();

        totalFare += getDistanceBasedFare(rideDistance);

        totalFare += getTimeBasedFare(ride.getDuration());

        totalFare += getServiceTaxOnFare(totalFare);

        return totalFare;
    }

    private static double getServiceTaxOnFare(double totalFare) {
        return CalculationUtil.roundDecimalToScale(Constants.SERVICE_TAX_ON_FARE * totalFare);
    }

    private static double getBasePrice() {
        return Constants.BASE_FARE;
    }

    private static double getDistanceBasedFare(double distance) {
        return CalculationUtil.roundDecimalToScale(Constants.FARE_PER_KM * distance);
    }

    private static double getTimeBasedFare(double timeInMinutes) {
        return CalculationUtil.roundDecimalToScale(Constants.FARE_PER_MINUTE * timeInMinutes);
    }
}
