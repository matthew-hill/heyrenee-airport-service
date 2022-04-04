package com.example.matthillheyrenee.airportservice.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class InvalidDistanceUnitException extends RuntimeException {

    public InvalidDistanceUnitException(String msg) {
        super(msg);
    }
}
