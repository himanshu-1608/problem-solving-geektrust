package com.example.geektrust.data_service.rider;

import com.example.geektrust.domain_models.user.Rider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RiderDataServiceImpl implements RiderDataService {

    private static RiderDataService riderDataService;

    public static RiderDataService getInstance() {
        if(Objects.isNull(riderDataService)) {
            riderDataService = new RiderDataServiceImpl();
        }
        return riderDataService;
    }

    private final List<Rider> riders = new ArrayList<>();

    @Override
    public Optional<Rider> getRiderById(String riderId) {
        return riders.stream().filter(rider -> riderId.equals(rider.getId())).findFirst();
    }

    @Override
    public Rider addRider(Rider rider) {
        riders.add(rider);
        return rider;
    }


}
