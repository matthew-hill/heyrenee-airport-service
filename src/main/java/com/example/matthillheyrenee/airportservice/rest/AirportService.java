package com.example.matthillheyrenee.airportservice.rest;

import com.example.matthillheyrenee.airportservice.model.Airport;
import org.springframework.http.ResponseEntity;

public interface AirportService {
    ResponseEntity<Airport> getAirportInfo(String code);
    ResponseEntity<AirportDistanceResponse> calculateAirportDistance(AirportDistanceRequest request);
}
