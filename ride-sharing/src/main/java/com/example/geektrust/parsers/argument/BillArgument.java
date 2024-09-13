package com.example.geektrust.parsers.argument;

public class BillArgument extends CommandArgument {

    private final String rideId;

    public BillArgument(String rideId) {
        this.rideId = rideId;
    }

    public String getRideId() {
        return rideId;
    }

}
