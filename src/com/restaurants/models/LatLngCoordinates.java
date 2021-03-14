package com.restaurants.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LatLngCoordinates {
    private double latitude;
    private double longitude;

    @JsonProperty("lat")
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("lng")
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
