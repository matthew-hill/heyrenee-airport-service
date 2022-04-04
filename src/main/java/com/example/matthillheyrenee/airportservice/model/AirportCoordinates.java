package com.example.matthillheyrenee.airportservice.model;

import lombok.Data;

@Data
public class AirportCoordinates {
    private Double originLatitude;
    private Double originLongitude;
    private Double destinationLatitude;
    private Double destinationLongitude;

    public AirportCoordinates(Airport origin, Airport destination) {
        this.originLatitude = origin.getLatitude();
        this.originLongitude = origin.getLongitude();
        this.destinationLatitude = destination.getLatitude();
        this.destinationLongitude = destination.getLongitude();
    }
}
