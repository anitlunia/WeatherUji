package com.example.oxana.weather;

/**
 * Created by Oxana on 17/04/2015.
 */
public class Location {

    private final String name;
    private final String country;
    private final double longitude;
    private final double latitude;

    public Location(String name, String country, double longitude, double latitude) {
        this.name = name;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getNameCountry() { return name + "," + country; }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getCoordinateString() {
        String r = "(";
        if (latitude >= 0)
            r += latitude + "º N";
        else
            r += - latitude + "º S";
        r+= ", ";
        if (longitude >= 0)
            r += longitude + "º E";
        else
            r += - longitude + "º W";
        r+= ")";
        return r;
    }
}
