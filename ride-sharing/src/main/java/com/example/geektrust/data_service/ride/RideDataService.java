package com.example.geektrust.data_service.ride;

import com.example.geektrust.domain_models.Ride;

import java.util.Optional;

public interface RideDataService {

    Optional<Ride> getRideById(String rideId);

    Ride addRide(Ride ride);
}
