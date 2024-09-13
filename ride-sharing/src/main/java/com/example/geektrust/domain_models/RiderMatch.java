package com.example.geektrust.domain_models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RiderMatch {

    private final String riderId;

    private final List<String> matchedDriverIds = new ArrayList<>();

    public RiderMatch(String riderId) {
        this.riderId = riderId;
    }

    public String getRiderId() {
        return riderId;
    }

    public List<String> getMatchedDriverIds() {
        return Collections.unmodifiableList(matchedDriverIds);
    }

    public void setMatchedDriverIds(List<String> matchedDriverIds) {
        this.matchedDriverIds.clear();
        this.matchedDriverIds.addAll(new ArrayList<>(matchedDriverIds));
    }
}