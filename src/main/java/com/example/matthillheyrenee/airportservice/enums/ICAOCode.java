package com.example.matthillheyrenee.airportservice.enums;

public enum ICAOCode {
    KORD ("KORD"),
    KJFK ("KJFK"),
    KLAX ("KLAX"),
    PAJN ("PAJN"),
    EGLL ("EGLL"),
    RJAA ("RJAA"),
    WSSS ("WSSS"),
    OTHH ("OTHH"),
    LFPG ("LFPG"),
    DGAA ("DGAA"),
    DEFAULT("");

    private final String code;

    ICAOCode(String code) {
        this.code = code;
    }
}
