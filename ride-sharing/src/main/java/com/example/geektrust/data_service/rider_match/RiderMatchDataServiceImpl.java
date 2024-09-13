package com.example.geektrust.data_service.rider_match;

import com.example.geektrust.domain_models.RiderMatch;
import com.example.geektrust.exception.InvalidArgumentException;

import java.util.*;

public class RiderMatchDataServiceImpl implements RiderMatchDataService {

    private static RiderMatchDataService riderMatchDataService;

    public static RiderMatchDataService getInstance() {
        if(Objects.isNull(riderMatchDataService)) {
            riderMatchDataService = new RiderMatchDataServiceImpl();
        }
        return riderMatchDataService;
    }

    private final Map<String, RiderMatch> riderMatchMap = new HashMap<>();

    @Override
    public Optional<RiderMatch> getMatchesForRider(String riderId) {
        return Optional.ofNullable(riderMatchMap.get(riderId));
    }

    @Override
    public RiderMatch addRiderMatch(RiderMatch riderMatch) {
        riderMatchMap.put(riderMatch.getRiderId(), riderMatch);
        return riderMatch;
    }
}
