package com.example.geektrust.processors;

import com.example.geektrust.data_service.driver.DriverDataService;
import com.example.geektrust.data_service.driver.DriverDataServiceImpl;
import com.example.geektrust.domain_models.user.Driver;
import com.example.geektrust.parsers.argument.AddDriverArgument;
import com.example.geektrust.parsers.argument.CommandArgument;

public class AddDriverCommandProcessor implements ICommandProcessor {

    private final DriverDataService driverDataService = DriverDataServiceImpl.getInstance();

    @Override
    public void process(CommandArgument commandArgument) {
        AddDriverArgument argument = (AddDriverArgument) commandArgument;
        Driver driver = new Driver(argument.getDriverId(), argument.getDriverXCoordinate(), argument.getDriverYCoordinate());
        driverDataService.addDriver(driver);
    }
}
