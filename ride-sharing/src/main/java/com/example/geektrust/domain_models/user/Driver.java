package com.example.geektrust.domain_models.user;

import com.example.geektrust.constants.DrivingStatus;
import com.example.geektrust.exception.DomainModelConstraintException;

public class Driver extends User {

    private DrivingStatus drivingStatus;

    public Driver(String id, Double locationXCoordinate, Double locationYCoordinate) {
        super(id, locationXCoordinate, locationYCoordinate);
        this.drivingStatus = DrivingStatus.AVAILABLE;
    }

    public DrivingStatus getDrivingStatus() {
        return this.drivingStatus;
    }

    public void startDriving() throws DomainModelConstraintException {
        if(!DrivingStatus.AVAILABLE.equals(this.drivingStatus)) {
            throw new DomainModelConstraintException();
        }
        this.drivingStatus = DrivingStatus.BOOKED;
    }

    public void stopDriving() throws DomainModelConstraintException {
        if(!DrivingStatus.BOOKED.equals(this.drivingStatus)) {
            throw new DomainModelConstraintException();
        }
        this.drivingStatus = DrivingStatus.AVAILABLE;
    }
}
