package com.example.matthillheyrenee.airportservice.rest;

import lombok.Data;

@Data
public class AirportDistanceResponse {
    private String originCode;
    private String destinationCode;
    private Double distance;
    private String units;

    public AirportDistanceResponse(AirportDistanceRequest request, Double distance) {
        this.originCode = request.getOriginCode();
        this.destinationCode = request.getDestinationCode();
        this.distance = distance;
        this.units = request.getUnits();
    }
}
