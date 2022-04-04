package com.example.matthillheyrenee.airportservice.util;

import com.example.matthillheyrenee.airportservice.enums.DistanceUnit;
import com.example.matthillheyrenee.airportservice.model.AirportCoordinates;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AirportUtils {

    public static final double radiusOfEarthKm = 6372.8;
    public static final double radiusOfEarthMiles = 3958.8;

    public static double calculateDistanceBetweenAirportsInKilometers(AirportCoordinates coordinates) {
        return calculateDistanceBetweenAirports(coordinates, DistanceUnit.KILOMETERS);
    }

    public static double calculateDistanceBetweenAirportsInMiles(AirportCoordinates coordinates) {
        return calculateDistanceBetweenAirports(coordinates, DistanceUnit.MILES);
    }

    protected static double calculateDistanceBetweenAirports(AirportCoordinates coordinates, DistanceUnit units) {

        //Haversine calculation for distance
        //https://community.esri.com/t5/coordinate-reference-systems-blog/distance-on-a-sphere-the-haversine-formula/ba-p/902128
        double deltaLatitudeInRadians = Math.toRadians(coordinates.getDestinationLatitude() - coordinates.getOriginLatitude());
        double deltaLongitudeInRadians = Math.toRadians(coordinates.getDestinationLongitude() - coordinates.getOriginLongitude());
        double originLatitudeInRadians = Math.toRadians(coordinates.getOriginLatitude());
        double destinationLatitudeInRadians = Math.toRadians(coordinates.getDestinationLatitude());

        double angleA = Math.pow(Math.sin(deltaLatitudeInRadians / 2),2) +
                        Math.cos(originLatitudeInRadians) *
                        Math.pow(Math.sin(deltaLongitudeInRadians / 2),2) *
                        Math.cos(destinationLatitudeInRadians);
        double angleC = 2 * Math.asin(Math.sqrt(angleA));

        double calculatedDistance = -1d;
        if (units == DistanceUnit.MILES) {
            calculatedDistance = radiusOfEarthMiles * angleC;
        } else if (units == DistanceUnit.KILOMETERS) {
            calculatedDistance = radiusOfEarthKm * angleC;
        }

        return roundDistanceValue(calculatedDistance);
    }

    private static Double roundDistanceValue(Double distance) {
        BigDecimal roundedDistance = new BigDecimal(distance).setScale(2, RoundingMode.HALF_UP);
        return roundedDistance.doubleValue();
    }
}
