package com.example.matthillheyrenee.airportservice.csv;

import com.example.matthillheyrenee.airportservice.model.Airport;
import com.example.matthillheyrenee.airportservice.dataaccess.AirportDatastore;
import com.example.matthillheyrenee.airportservice.dataaccess.SimpleAirportDatastore;
import com.example.matthillheyrenee.airportservice.enums.IATACode;
import com.example.matthillheyrenee.airportservice.enums.ICAOCode;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.List;

public class CsvImporterTests {

    private static final String TEST_FILE_PATH = "src/main/resources/static/airport_data_test.csv";

    CsvImporter csvImporter = new SimpleCsvImporter();
    AirportDatastore datastore = new SimpleAirportDatastore();

    @Test
    public void testCsvParsing() throws IOException {
        csvImporter.queueNewFileToParse(TEST_FILE_PATH);
        List<Airport> list = csvImporter.getParsedAirportData();

        for (Airport airport : list) {
            datastore.addAirportById(airport);
            datastore.addAirportByIataCode(airport);
            datastore.addAirportByIcaoCode(airport);
        }

        Airport juneau = datastore.getAirportById(4);
        Assert.assertEquals(juneau.getName(), "Juneau International Airport");
        Assert.assertEquals(juneau.getCity(), "Juneau");
        Assert.assertEquals(juneau.getCountry(), "United States");
        Assert.assertEquals(juneau.getIataCode(), IATACode.valueOf("45436rrg"));
        Assert.assertEquals(juneau.getIcaoCode(), ICAOCode.PAJN);
        Assert.assertTrue(juneau.getLatitude() == 58.35499954223633d);
        Assert.assertTrue(juneau.getLongitude() == -134.5760040283203d);
        Assert.assertTrue(juneau.getAltitude() == 21);
        Assert.assertEquals(juneau.getTimeZone(), "America/Anchorage");

        Assert.assertEquals("IATA Code 'ACC' must equal Airport 10", datastore.getAirportByIataCode(IATACode.ACC), datastore.getAirportById(10));
        Assert.assertEquals("ICAO Code 'KJFK' must equal Airport 2", datastore.getAirportByIcaoCode(ICAOCode.KJFK), datastore.getAirportById(2));
    }
}
