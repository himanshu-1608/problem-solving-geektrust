package com.example.geektrust.data_service.rider_match;

import com.example.geektrust.domain_models.RiderMatch;
import com.example.geektrust.exception.InvalidArgumentException;

import java.util.Optional;

public interface RiderMatchDataService {

    Optional<RiderMatch> getMatchesForRider(String riderId);

    RiderMatch addRiderMatch(RiderMatch riderMatch);
}
