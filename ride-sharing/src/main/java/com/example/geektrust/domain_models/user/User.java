package com.example.geektrust.domain_models.user;

public abstract class User {

    protected final String id;

    protected final Double locationXCoordinate;

    protected final Double locationYCoordinate;

    protected User(String id, Double locationXCoordinate, Double locationYCoordinate) {
        this.id = id;
        this.locationXCoordinate = locationXCoordinate;
        this.locationYCoordinate = locationYCoordinate;
    }

    public String getId() {
        return this.id;
    }

    public Double getLocationXCoordinate() {
        return this.locationXCoordinate;
    }

    public Double getLocationYCoordinate() {
        return this.locationYCoordinate;
    }

}