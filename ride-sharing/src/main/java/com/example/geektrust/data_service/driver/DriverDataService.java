package com.example.geektrust.data_service.driver;

import com.example.geektrust.domain_models.user.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverDataService {

    Optional<Driver> getDriverById(String driverId);

    Driver addDriver(Driver driver);

    List<Driver> getAllAvailableDrivers();
}
