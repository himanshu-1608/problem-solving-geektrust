package com.example.geektrust.parsers.argument;

public class AddDriverArgument extends CommandArgument {

    private final String driverId;
    private final double driverXCoordinate;
    private final double driverYCoordinate;

    public AddDriverArgument(String driverId, double xCoordinate, double yCoordinate) {
        this.driverId = driverId;
        this.driverXCoordinate = xCoordinate;
        this.driverYCoordinate = yCoordinate;
    }

    public String getDriverId() {
        return driverId;
    }

    public double getDriverXCoordinate() {
        return driverXCoordinate;
    }

    public double getDriverYCoordinate() {
        return driverYCoordinate;
    }

}
