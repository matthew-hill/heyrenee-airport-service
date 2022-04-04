package com.example.matthillheyrenee.airportservice.rest;

import com.example.matthillheyrenee.airportservice.model.Airport;
import com.example.matthillheyrenee.airportservice.dataaccess.AirportDatastore;
import com.example.matthillheyrenee.airportservice.dataaccess.SimpleAirportDatastore;
import com.example.matthillheyrenee.airportservice.facade.AirportFacade;
import com.example.matthillheyrenee.airportservice.rest.exception.InvalidAirportCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping(path="/airport", produces="application/json")
public class AirportServiceV1 implements AirportService {

    Logger logger = LoggerFactory.getLogger(AirportService.class);

    private final AirportFacade airportFacade;

    public AirportServiceV1() throws IOException {
        AirportDatastore cache = new SimpleAirportDatastore();
        try {
            cache.initialize();
        } catch (FileNotFoundException ex) {
            String error = "Severe error, failed to load initial airport data, cannot start service!";
            logger.error(error, ex);
            throw new IOException(error, ex);
        }
        AirportFacade airportFacade = new AirportFacade(cache);
        this.airportFacade = airportFacade;
    }

    @GetMapping("/info/{code}")
    public ResponseEntity<Airport> getAirportInfo(@PathVariable("code") String code) {
        logger.info("Getting data for airport code = " + code);

        Airport airport;
        airport = airportFacade.lookupAirportByCode(code);

        if (airport != null) {
            logger.debug("Found data for airport code = " + code);
            logger.debug(airport.toString());
        } else {
            //log error
        }

        return ResponseEntity.status(HttpStatus.OK).body(airport);
    }

    @GetMapping("/distance")
    public ResponseEntity<AirportDistanceResponse>  calculateAirportDistance(@RequestBody AirportDistanceRequest request) {
        logger.info("Getting distance between two airports - origin: [{}] and destination: [{}]", request.getOriginCode(), request.getDestinationCode());

        ResponseEntity<AirportDistanceResponse> response = ResponseEntity.notFound().build();

        Double distance = airportFacade.calculateDistanceBetweenAirports(request);

        if (distance > 0d) {
            AirportDistanceResponse distanceResponse = new AirportDistanceResponse(request, distance);
            response = ResponseEntity.ok().body(distanceResponse);
        } else {
            logger.error("Unable to calculate distance for specified airport codes: '{}' and '{}'.", request.getOriginCode(), request.getDestinationCode());
            throw new InvalidAirportCodeException("Unable to calculate distance for specified airport codes + '" + request.getOriginCode() + "' and '" + request.getDestinationCode()+ "'.");
        }

        return response;
    }

}
