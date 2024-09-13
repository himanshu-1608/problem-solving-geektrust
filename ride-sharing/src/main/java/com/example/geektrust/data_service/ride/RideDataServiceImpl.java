package com.example.geektrust.data_service.ride;

import com.example.geektrust.domain_models.Ride;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RideDataServiceImpl implements RideDataService {

    private static RideDataService rideDataService;

    public static RideDataService getInstance() {
        if (Objects.isNull(rideDataService)) {
            rideDataService = new RideDataServiceImpl();
        }
        return rideDataService;
    }

    private final List<Ride> rides = new ArrayList<>();

    @Override
    public Optional<Ride> getRideById(String rideId) {
        return rides.stream().filter(ride -> rideId.equals(ride.getId())).findFirst();
    }

    @Override
    public Ride addRide(Ride ride) {
        rides.add(ride);
        return ride;
    }
}
