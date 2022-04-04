package com.example.matthillheyrenee.airportservice.enums;

public enum DistanceUnit {
    MILES("miles"),
    KILOMETERS("kilometers"),
    DEFAULT("");

    private final String unit;

    DistanceUnit(String unit) {
        this.unit = unit;
    }
}
