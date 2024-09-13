package com.example.geektrust.parsers.argument;

public class StartRideArgument extends CommandArgument {

    private final String rideId;
    private final int driverIndex;
    private final String riderId;

    public StartRideArgument(String rideId, int driverIndex, String riderId) {
        this.rideId = rideId;
        this.driverIndex = driverIndex;
        this.riderId = riderId;
    }

    public String getRiderId() {
        return riderId;
    }

    public String getRideId() {
        return rideId;
    }

    public int getDriverIndex() {
        return driverIndex;
    }

}
