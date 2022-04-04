package com.example.matthillheyrenee.airportservice.csv;

import com.example.matthillheyrenee.airportservice.model.Airport;

import java.io.FileNotFoundException;
import java.util.List;

public interface CsvImporter {

    void queueNewFileToParse() throws FileNotFoundException;
    void queueNewFileToParse(String file) throws FileNotFoundException;
    List<Airport> getParsedAirportData();
}
