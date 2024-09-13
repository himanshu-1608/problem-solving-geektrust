package com.example.geektrust.parsers.argument;

public class StopRideArgument extends CommandArgument {

    private final String rideId;
    private final double destinationXCoordinate;
    private final double destinationYCoordinate;
    private final long duration;

    public StopRideArgument(String rideId, double destinationXCoordinate, double destinationYCoordinate, long duration) {
        this.rideId = rideId;
        this.destinationXCoordinate = destinationXCoordinate;
        this.destinationYCoordinate = destinationYCoordinate;
        this.duration = duration;
    }

    public String getRideId() {
        return rideId;
    }

    public double getDestinationXCoordinate() {
        return destinationXCoordinate;
    }

    public double getDestinationYCoordinate() {
        return destinationYCoordinate;
    }

    public long getDuration() {
        return duration;
    }

}
