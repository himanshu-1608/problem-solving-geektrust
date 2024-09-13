package com.example.geektrust.constants;

public final class Constants {

    private Constants() {}

    public static final String COMMAND_DELIMITER = " ";

    public static final int ARGUMENT_0 = 0;
    public static final int ARGUMENT_1 = 1;
    public static final int ARGUMENT_2 = 2;
    public static final int ARGUMENT_3 = 3;
    public static final int ARGUMENT_4 = 4;

    public static final int ADD_DRIVER_ARGUMENT_SIZE = 4;
    public static final int ADD_RIDER_ARGUMENT_SIZE = 4;
    public static final int BILL_ARGUMENT_SIZE = 2;
    public static final int MATCH_ARGUMENT_SIZE = 2;
    public static final int START_RIDE_ARGUMENT_SIZE = 4;
    public static final int STOP_RIDE_ARGUMENT_SIZE = 5;

    public static final int STANDARD_REDUCE_INDEX = 1;


    public static final String INVALID_ARGUMENT_EXCEPTION_MESSAGE = "Invalid Command Line Arguments";
    public static final String DOMAIN_MODEL_UPDATE_CONSTRAINT_EXCEPTION_MESSAGE = "Invalid Operation on Domain Model";
    public static final String INVALID_RIDE_MESSAGE = "INVALID_RIDE";
    public static final String RIDE_NOT_COMPLETED_MESSAGE = "RIDE_NOT_COMPLETED";

    public static final String MATCH_COMMAND_NO_DRIVER_ERROR = "NO_DRIVERS_AVAILABLE";
    public static final String MATCH_COMMAND_SUCCESS_MESSAGE = "DRIVERS_MATCHED ";
    public static final String START_RIDE_COMMAND_SUCCESS_MESSAGE = "RIDE_STARTED ";
    public static final String STOP_RIDE_COMMAND_SUCCESS_MESSAGE = "RIDE_STOPPED ";
    public static final String BILL_COMMAND_SUCCESS_MESSAGE = "BILL";

    public static final double START_FARE = 0;
    public static final double BASE_FARE = 50;
    public static final double FARE_PER_KM = 6.5;
    public static final double FARE_PER_MINUTE = 2;
    public static final double SERVICE_TAX_ON_FARE = 0.2;

    public static final double MAX_ALLOWED_DISTANCE = 5.00;
    public static final int MAX_ALLOWED_DRIVERS = 5;

    public static final int COMPARATOR_LOW = -1;
    public static final int COMPARATOR_HIGH = 1;

    public static final int ROUND_OFF_SCALE = 2;
    public static final String ROUND_OFF_DECIMAL_FORMAT = "#.##";
}
