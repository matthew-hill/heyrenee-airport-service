package com.example.matthillheyrenee.airportservice.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AirportDistanceRequest {
    @JsonProperty
    private String originCode;
    @JsonProperty
    private String destinationCode;
    @JsonProperty
    private String units;

    public AirportDistanceRequest(String originCode, String destinationCode, String units) {
        this.originCode = originCode;
        this.destinationCode = destinationCode;
        this.units = units;
    }

    public String getCacheKey()  {
        if (originCode.compareTo(destinationCode) < 0) {
            return destinationCode + originCode + units;
        } else {
            return originCode + destinationCode + units;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final AirportDistanceRequest other = (AirportDistanceRequest) obj;
        if(this.originCode != other.originCode) {
            return false;
        }

        if(this.destinationCode != other.destinationCode) {
            return false;
        }


        if (this.units != other.units) {
            return false;
        }

        return true;
    }
}
