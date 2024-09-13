package com.example.geektrust.domain_models;

import com.example.geektrust.constants.RideStatus;

public class Ride {

    private final String id;

    private final String riderId;

    private final String driverId;

    private final double sourceXCoordinate;

    private final double sourceYCoordinate;

    private Double destinationXCoordinate;

    private Double destinationYCoordinate;

    private Long duration;

    private RideStatus status;

    public Ride(String id, String riderId, String driverId, Double sourceXCoordinate, Double sourceYCoordinate) {
        this.id = id;
        this.riderId = riderId;
        this.driverId = driverId;
        this.sourceXCoordinate = sourceXCoordinate;
        this.sourceYCoordinate = sourceYCoordinate;
        this.status = RideStatus.STARTED;
    }

    public void completeRide(Double destinationXCoordinate, Double destinationYCoordinate, Long duration) {
        this.destinationXCoordinate = destinationXCoordinate;
        this.destinationYCoordinate = destinationYCoordinate;
        this.duration = duration;
        this.status = RideStatus.COMPLETED;
    }

    public String getId() {
        return id;
    }

    public RideStatus getStatus() {
        return status;
    }

    public Double getSourceXCoordinate() {
        return sourceXCoordinate;
    }

    public Double getSourceYCoordinate() {
        return sourceYCoordinate;
    }

    public Double getDestinationXCoordinate() {
        return destinationXCoordinate;
    }

    public Double getDestinationYCoordinate() {
        return destinationYCoordinate;
    }

    public Long getDuration() {
        return duration;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getRiderId() {
        return riderId;
    }
}
