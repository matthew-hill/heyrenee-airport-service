package com.example.matthillheyrenee.airportservice.rest;

import lombok.Data;

@Data
public class AirportDistanceResponse {
    private String originCode;
    private String destinationCode;
    private String units;
    private Double distance;

    public AirportDistanceResponse(AirportDistanceRequest request, Double distance) {
        this.originCode = request.getOriginCode();
        this.destinationCode = request.getDestinationCode();
        this.units = request.getUnits();
        this.distance = distance;
    }
}
