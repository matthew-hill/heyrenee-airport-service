package com.example.matthillheyrenee.airportservice.facade;

import com.example.matthillheyrenee.airportservice.model.Airport;
import com.example.matthillheyrenee.airportservice.dataaccess.AirportDatastore;
import com.example.matthillheyrenee.airportservice.enums.IATACode;
import com.example.matthillheyrenee.airportservice.enums.ICAOCode;
import com.example.matthillheyrenee.airportservice.rest.AirportDistanceRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AirportFacadeTests {

    @Mock
    AirportDatastore mockedAirportDatastore;

    @InjectMocks
    AirportFacade airportFacade;

    Airport mockedAirportData;

    Airport mockedOriginCoordinates;
    Airport mockedDestinationCoordinates;

    @Before
    public void setup() {
        //data test
        when(mockedAirportDatastore.getAirportByIcaoCode(ICAOCode.KJFK)).thenReturn(getMockedJFKAirportData());
        when(mockedAirportDatastore.getAirportByIataCode(IATACode.JNU)).thenReturn(getMockedJNUAirportData());

        //coordinates test
        when(mockedAirportDatastore.getAirportByIataCode(IATACode.ACC)).thenReturn(getMockedOriginCoordinates());
        when(mockedAirportDatastore.getAirportByIcaoCode(ICAOCode.LFPG)).thenReturn(getMockedDestinationCoordinates());
    }

    @Test
    public void testLookupAirportByCode_badLengthCode() {
        Airport foundAirport = airportFacade.lookupAirportByCode("oatirghjdfkl");
        Assert.assertTrue("Airport code 'oatirghjdfkl' should not find any airports nor throw an error",
                null == foundAirport);
    }

    @Test
    public void testLookupAirportByCode_badIATACode() {
        Airport foundAirport = airportFacade.lookupAirportByCode("YUT");
        Assert.assertTrue("IATA Code 'YUT' should not find any airports nor throw an error",
                null == foundAirport);
    }

    @Test
    public void testLookupAirportByCode_badICAOCode() {
        Airport foundAirport = airportFacade.lookupAirportByCode("POUY");
        Assert.assertTrue("ICAO Code 'POUY' should not find any airports nor throw an error",
                null == foundAirport);
    }

    @Test
    public void testGetAirportData() {
        Airport foundAirport = airportFacade.lookupAirportByCode("KJFK");
        Assert.assertEquals("Found Airport data does not match mocked data",
                getMockedJFKAirportData(), foundAirport);
    }

    @Test
    public void testCalculateDistance() {
        Double distanceMiles = airportFacade.calculateDistanceBetweenAirports(new AirportDistanceRequest("KJFK", "JNU", "mi"));
        Assert.assertTrue("Calculated mileage distance from KFJK to PNJ is incorrect", 2868.9d == distanceMiles);

        Double distanceKilometers = airportFacade.calculateDistanceBetweenAirports(new AirportDistanceRequest("KJFK", "JNU", "km"));
        Assert.assertTrue("Calculated kilometers distance from KFJK to PNJ is incorrect", 4618.29d == distanceKilometers);
    }

    protected Airport getMockedJFKAirportData() {
        if (mockedAirportData == null) {
            mockedAirportData = new Airport();
            mockedAirportData.setId(10);
            mockedAirportData.setCity("Any City");
            mockedAirportData.setCountry("Any Country");
            mockedAirportData.setIataCode(IATACode.JFK);
            mockedAirportData.setIcaoCode(ICAOCode.KJFK);
            mockedAirportData.setLatitude(40.63980103D);
            mockedAirportData.setLongitude(-73.77890015D);
            mockedAirportData.setAltitude(100);
            mockedAirportData.setTimeZone("America/Los Angeles");
        }

        return mockedAirportData;
    }

    protected Airport getMockedJNUAirportData() {
        if (mockedAirportData == null) {
            mockedAirportData = new Airport();
            mockedAirportData.setId(11);
            mockedAirportData.setCity("Any City");
            mockedAirportData.setCountry("Any Country");
            mockedAirportData.setIataCode(IATACode.JNU);
            mockedAirportData.setIcaoCode(ICAOCode.PAJN);
            mockedAirportData.setLatitude(58.35499954223633D);
            mockedAirportData.setLongitude(-134.5760040283203D);
            mockedAirportData.setAltitude(100);
            mockedAirportData.setTimeZone("America/Los Angeles");
        }

        return mockedAirportData;
    }

    protected Airport getMockedOriginCoordinates() {
        if (mockedOriginCoordinates == null) {
            mockedOriginCoordinates = new Airport();
            mockedOriginCoordinates.setId(10);
            mockedOriginCoordinates.setIataCode(IATACode.JFK);
            mockedOriginCoordinates.setIcaoCode(ICAOCode.KJFK);
            mockedOriginCoordinates.setLatitude(40.63980103D);
            mockedOriginCoordinates.setLongitude(-73.77890015D);
        }

        return mockedOriginCoordinates;
    }

    protected Airport getMockedDestinationCoordinates() {
        if (mockedDestinationCoordinates == null) {
            mockedDestinationCoordinates = new Airport();
            mockedDestinationCoordinates.setId(11);
            mockedDestinationCoordinates.setIataCode(IATACode.JNU);
            mockedDestinationCoordinates.setIcaoCode(ICAOCode.PAJN);
            mockedDestinationCoordinates.setLatitude(58.35499954223633D);
            mockedDestinationCoordinates.setLongitude(-134.5760040283203D);
        }

        return mockedDestinationCoordinates;
    }
}
