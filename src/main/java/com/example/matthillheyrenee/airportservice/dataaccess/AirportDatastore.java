package com.example.matthillheyrenee.airportservice.dataaccess;

import com.example.matthillheyrenee.airportservice.model.Airport;
import com.example.matthillheyrenee.airportservice.enums.IATACode;
import com.example.matthillheyrenee.airportservice.enums.ICAOCode;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

public interface AirportDatastore {
    void initialize() throws FileNotFoundException;

    void addAirportById(Airport airport);
    void addAirportByIataCode(Airport airport);
    void addAirportByIcaoCode(Airport airport);

    Airport getAirportById(Integer id);
    Airport getAirportByIataCode(IATACode iataCode);
    Airport getAirportByIcaoCode(ICAOCode icaoCode);
}