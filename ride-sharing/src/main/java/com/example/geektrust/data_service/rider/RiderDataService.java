package com.example.geektrust.data_service.rider;

import com.example.geektrust.domain_models.user.Rider;

import java.util.Optional;

public interface RiderDataService {

    Optional<Rider> getRiderById(String riderId);

    Rider addRider(Rider rider);
}
