package com.example.matthillheyrenee.airportservice.util;

import com.example.matthillheyrenee.airportservice.enums.DistanceUnit;
import com.example.matthillheyrenee.airportservice.enums.IATACode;
import com.example.matthillheyrenee.airportservice.enums.ICAOCode;

import com.example.matthillheyrenee.airportservice.rest.exception.InvalidAirportCodeException;
import com.example.matthillheyrenee.airportservice.rest.exception.InvalidDistanceUnitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class AirportValidator {

    static Logger logger = LoggerFactory.getLogger(AirportValidator.class);

    public static void validateAirportsForDistance(String originCode, String destinationCode, String units) {

        //create validator
        if (!isValidAirportCode(originCode)) {
            //log an error and return empty results
            logger.error("Origin Airport Code '{}' is not a valid airport code.", originCode);
            throw new InvalidAirportCodeException("Origin Airport Code '" + originCode + "' is not a valid airport code.");

        } else if(!isValidAirportCode(destinationCode)) {
            //log an error and return empty results
            logger.error("Destination Airport Code '{}' is not a valid airport code.", destinationCode);
            throw new InvalidAirportCodeException("Destination Airport Code '" + destinationCode + "' is not a valid airport code.");
        }


        try {
            if (units == null) {
                throw getInvalidDistanceUnitException(units);
            } else {
                DistanceUnit unitsToValidate = DistanceUnit.valueOf(units.toUpperCase());

                if (unitsToValidate == DistanceUnit.DEFAULT) {
                    throw getInvalidDistanceUnitException(units);
                }
            }



        } catch (IllegalArgumentException ex) {
            throw getInvalidDistanceUnitException(units);
        }
    }

    public static boolean isValidAirportCode(String code) {
        return StringUtils.hasText(code) &&
                (isValidIataCode(code) || isValidIcaoCode(code));
    }

    public static boolean isValidIataCode(String code) {
        return code.length() == 3 && getValidIataCode(code) != IATACode.DEFAULT;
    }

    public static boolean isValidIcaoCode(String code) {
        return code.length() == 4 && getValidIcaoCode(code) != ICAOCode.DEFAULT;
    }

    protected static IATACode getValidIataCode(String code) {
        IATACode matchedCode = IATACode.DEFAULT;

        try {
            matchedCode = IATACode.valueOf(code);
        } catch (IllegalArgumentException ex) {
            logger.error("Unable to parse IATA code from input: [" + code + "]", ex);
        }

        return matchedCode;
    }

    protected static ICAOCode getValidIcaoCode(String code) {
        ICAOCode matchedCode = ICAOCode.DEFAULT;

        try {
            matchedCode = ICAOCode.valueOf(code);
        } catch (IllegalArgumentException ex) {
            logger.error("Unable to parse ICAO code from input: [" + code + "]", ex);
        }

        return matchedCode;
    }

    protected static InvalidDistanceUnitException getInvalidDistanceUnitException(String units) {
        logger.error("Unit value '{}' is not a valid unit type.", units);
        return new InvalidDistanceUnitException("Unit value '" + units + "' is not a valid unit type.");
    }
}
