package com.example.matthillheyrenee.airportservice.facade;

import com.example.matthillheyrenee.airportservice.enums.DistanceUnit;
import com.example.matthillheyrenee.airportservice.model.Airport;
import com.example.matthillheyrenee.airportservice.dataaccess.AirportDatastore;
import com.example.matthillheyrenee.airportservice.enums.IATACode;
import com.example.matthillheyrenee.airportservice.enums.ICAOCode;
import com.example.matthillheyrenee.airportservice.model.AirportCoordinates;
import com.example.matthillheyrenee.airportservice.rest.AirportDistanceRequest;
import com.example.matthillheyrenee.airportservice.util.AirportUtils;
import com.example.matthillheyrenee.airportservice.util.AirportValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AirportFacade {

    Logger logger = LoggerFactory.getLogger(AirportFacade.class);

    private final AirportDatastore airportDatastore;

    public AirportFacade(AirportDatastore airportDatastore) {
        this.airportDatastore = airportDatastore;
    }

    public Airport lookupAirportByCode(String code) {
        logger.debug("Lookup airport by code '{}'", code);

        Airport foundAirport = null;

        if (AirportValidator.isValidIataCode(code)) {
            foundAirport = airportDatastore.getAirportByIataCode(IATACode.valueOf(code));
        } else if (AirportValidator.isValidIcaoCode(code)) {
            foundAirport = airportDatastore.getAirportByIcaoCode(ICAOCode.valueOf(code));
        }

        return foundAirport;
    }

    @Cacheable(value="airportDistanceRequests", key="#distanceRequest.getCacheKey()")
    public Double calculateDistanceBetweenAirports(AirportDistanceRequest distanceRequest) {
        logger.debug("Calculate distance between airports '{}' and '{}'", distanceRequest.getOriginCode(), distanceRequest.getDestinationCode());

        String originCode = distanceRequest.getOriginCode();
        String destinationCode = distanceRequest.getDestinationCode();
        String units = distanceRequest.getUnits();

        AirportValidator.validateAirportsForDistance(originCode,destinationCode, units);

        Double distance = -1d;
        DistanceUnit unitsToCalculate = DistanceUnit.valueOf(units.toUpperCase());

        Airport originAirport = lookupAirportByCode(originCode);
        Airport destinationAirport = lookupAirportByCode(destinationCode);

        if (originAirport != null && destinationAirport != null &&
                originAirport.getId() != destinationAirport.getId()) {
            AirportCoordinates coordinates = new AirportCoordinates(originAirport, destinationAirport);

            if (unitsToCalculate == DistanceUnit.MILES) {
                distance = calculateDistanceInMiles(coordinates);
            } else if (unitsToCalculate == DistanceUnit.KILOMETERS) {
                distance = calculateDistanceInKilometers(coordinates);
            }
        }

        if (distance < 0d) {
            //throw exception here?
        }

        return distance;
    }

    private Double calculateDistanceInMiles(AirportCoordinates coordinates) {
        return AirportUtils.calculateDistanceBetweenAirportsInMiles(coordinates);
    }

    private Double calculateDistanceInKilometers(AirportCoordinates coordinates) {
        return AirportUtils.calculateDistanceBetweenAirportsInKilometers(coordinates);
    }

}
