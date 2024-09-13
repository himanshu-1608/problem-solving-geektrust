package com.example.geektrust.parsers.argument;

public class MatchArgument extends CommandArgument {

    private final String riderId;

    public MatchArgument(String riderId) {
        this.riderId = riderId;
    }

    public String getRiderId() {
        return riderId;
    }

}
