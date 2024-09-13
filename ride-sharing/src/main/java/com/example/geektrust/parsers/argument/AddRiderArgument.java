package com.example.geektrust.parsers.argument;

public class AddRiderArgument  extends CommandArgument {

    private final String riderId;
    private final Double riderXCoordinate;
    private final Double riderYCoordinate;

    public AddRiderArgument(String riderId, Double xCoordinate, Double yCoordinate) {
        this.riderId = riderId;
        this.riderXCoordinate = xCoordinate;
        this.riderYCoordinate = yCoordinate;
    }

    public String getRiderId() {
        return riderId;
    }

    public Double getRiderXCoordinate() {
        return riderXCoordinate;
    }

    public Double getRiderYCoordinate() {
        return riderYCoordinate;
    }

}
