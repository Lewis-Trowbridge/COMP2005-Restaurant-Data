package com.restaurants.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LatLngCoordinates {
    private double latitude;
    private double longitude;

    @JsonCreator
    public LatLngCoordinates(){

    }

    public LatLngCoordinates(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if (!(obj instanceof LatLngCoordinates)){
            return false;
        }

        LatLngCoordinates newCoordinates = (LatLngCoordinates)obj;

        return this.latitude == newCoordinates.latitude
                && this.longitude == newCoordinates.longitude;
    }
}
