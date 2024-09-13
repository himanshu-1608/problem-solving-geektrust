package com.example.geektrust.utils;

import com.example.geektrust.constants.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalculationUtil {

    public static double getDistance(double sourceXCoordinate, double sourceYCoordinate,
                                     double destinationXCoordinate, double destinationYCoordinate) {
        double xDiff = (destinationXCoordinate - sourceXCoordinate);
        xDiff *= xDiff;
        double yDiff = (destinationYCoordinate - sourceYCoordinate);
        yDiff *= yDiff;

        double sqrt = Math.sqrt(xDiff + yDiff);
        return roundDecimalToScale(sqrt);
    }

    public static double roundDecimalToScale(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(Constants.ROUND_OFF_SCALE, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String getRoundedValueForDecimal(double value) {
        DecimalFormat df = new DecimalFormat(Constants.ROUND_OFF_DECIMAL_FORMAT);
        df.setMinimumFractionDigits(Constants.ROUND_OFF_SCALE);
        return df.format(value);
    }
}
