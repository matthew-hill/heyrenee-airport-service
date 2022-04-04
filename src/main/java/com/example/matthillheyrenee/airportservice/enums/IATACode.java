package com.example.matthillheyrenee.airportservice.enums;

public enum IATACode {
    ORD ("ORD"),
    JFK ("JFK"),
    LAX ("LAX"),
    JNU ("JNU"),
    LHR ("LHR"),
    NRT ("NRT"),
    SIN ("SIN"),
    DOH ("DOH"),
    CDG ("CDG"),
    ACC ("ACC"),
    DEFAULT("");

    private final String code;

    IATACode(String code) {
        this.code = code;
    }
}
