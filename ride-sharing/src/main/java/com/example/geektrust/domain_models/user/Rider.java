package com.example.geektrust.domain_models.user;

import com.example.geektrust.constants.RidingStatus;
import com.example.geektrust.exception.DomainModelConstraintException;

public class Rider extends User {

    private RidingStatus ridingStatus;

    public Rider(String id, Double locationXCoordinate, Double locationYCoordinate) {
        super(id, locationXCoordinate, locationYCoordinate);
        this.ridingStatus = RidingStatus.IDLE;
    }

    public RidingStatus getRidingStatus() {
        return this.ridingStatus;
    }

    public void startRiding() throws DomainModelConstraintException {
        if(!RidingStatus.IDLE.equals(this.ridingStatus)) {
            throw new DomainModelConstraintException();
        }
        this.ridingStatus = RidingStatus.RIDING;
    }

    public void stopRiding() throws DomainModelConstraintException {
        if(!RidingStatus.RIDING.equals(this.ridingStatus)) {
            throw new DomainModelConstraintException();
        }
        this.ridingStatus = RidingStatus.IDLE;
    }

}