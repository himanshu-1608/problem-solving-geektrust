package com.example.geektrust.data_service.driver;

import com.example.geektrust.constants.DrivingStatus;
import com.example.geektrust.domain_models.user.Driver;

import java.util.*;
import java.util.stream.Collectors;

public class DriverDataServiceImpl implements DriverDataService {

    private static DriverDataService driverDataService;

    public static DriverDataService getInstance() {
        if(Objects.isNull(driverDataService)) {
            driverDataService = new DriverDataServiceImpl();
        }
        return driverDataService;
    }

    private final List<Driver> drivers = new ArrayList<>();


    @Override
    public Optional<Driver> getDriverById(String driverId) {
        return drivers.stream().filter(driver -> driverId.equals(driver.getId())).findFirst();
    }

    @Override
    public Driver addDriver(Driver driver) {
        drivers.add(driver);
        return driver;
    }

    @Override
    public List<Driver> getAllAvailableDrivers() {

        return drivers.stream()
                .filter(driver -> DrivingStatus.AVAILABLE.equals(driver.getDrivingStatus()))
                .collect(Collectors.toList());
    }
}
