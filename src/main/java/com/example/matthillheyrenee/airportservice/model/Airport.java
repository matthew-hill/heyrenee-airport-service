package com.example.matthillheyrenee.airportservice.model;

import com.example.matthillheyrenee.airportservice.enums.IATACode;
import com.example.matthillheyrenee.airportservice.enums.ICAOCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class Airport implements Serializable {

    private Integer id;
    private String name;
    private String city;
    private String country;
    private IATACode iataCode;
    private ICAOCode icaoCode;
    private Double latitude;
    private Double longitude;
    private Integer altitude;
    private String timeZone;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Airport compare = (Airport) obj;

        if (this.id != compare.id || this.name != compare.name ||
            this.city != compare.city || this.country != compare.country ||
            this.iataCode != compare.iataCode || this.icaoCode != compare.icaoCode ||
            this.latitude != compare.latitude || this.longitude != compare.longitude ||
            this.altitude != compare.altitude || this.timeZone != compare.timeZone) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return getId() + ", " +
                getName() + ", " +
                getCity()  + ", " +
                getCountry()  + ", " +
                getIataCode() + ", " +
                getIcaoCode()  + ", " +
                getLatitude() + ", " +
                getLongitude() + ", " +
                getAltitude() + ", " +
                getTimeZone();
    }
}
