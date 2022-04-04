package com.example.matthillheyrenee.airportservice.dataaccess;

import com.example.matthillheyrenee.airportservice.csv.CsvImporter;
import com.example.matthillheyrenee.airportservice.csv.SimpleCsvImporter;
import com.example.matthillheyrenee.airportservice.model.Airport;
import com.example.matthillheyrenee.airportservice.enums.IATACode;
import com.example.matthillheyrenee.airportservice.enums.ICAOCode;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SimpleAirportDatastore implements AirportDatastore {

    Map<Integer, Airport> airportById = new HashMap<>();
    Map<IATACode, Airport> airportByIataCode = new HashMap<>();
    Map<ICAOCode, Airport> airportByIcaoCode = new HashMap<>();

    public void initialize() throws FileNotFoundException {
        CsvImporter csvImporter = new SimpleCsvImporter();
        csvImporter.queueNewFileToParse();
        List<Airport> list = csvImporter.getParsedAirportData();

        for (Airport airport : list) {
            addAirportById(airport);
            addAirportByIataCode(airport);
            addAirportByIcaoCode(airport);
        }
    }

    public void addAirportById(Airport airport) {
        Integer id = airport.getId();
        if (airportById.get(id) == null) {
            airportById.put(id, airport);
        }
    }

    public void addAirportByIataCode(Airport airport) {
        IATACode code = airport.getIataCode();
        if (airportByIataCode.get(code) == null) {
            airportByIataCode.put(code, airport);
        }
    }

    public void addAirportByIcaoCode(Airport airport) {
        ICAOCode code = airport.getIcaoCode();
        if (airportByIcaoCode.get(code) == null) {
            airportByIcaoCode.put(code, airport);
        }
    }

    public Airport getAirportById(Integer id) {
        return airportById.get(id);
    }

    public Airport getAirportByIataCode(IATACode iataCode) {
        return airportByIataCode.get(iataCode);
    }

    public Airport getAirportByIcaoCode(ICAOCode icaoCode) {
        return airportByIcaoCode.get(icaoCode);
    }
}
