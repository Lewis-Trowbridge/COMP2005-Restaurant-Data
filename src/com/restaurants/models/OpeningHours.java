package com.restaurants.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpeningHours {
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;

    @JsonProperty("Monday")
    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    @JsonProperty("Tuesday")
    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    @JsonProperty("Wednesday")
    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    @JsonProperty("Thursday")
    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    @JsonProperty("Friday")
    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    @JsonProperty("Saturday")
    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    @JsonProperty("Sunday")
    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if (!(obj instanceof OpeningHours)){
            return false;
        }

        OpeningHours newHours = (OpeningHours)obj;

        return this.monday.equals(newHours.monday)
                && this.tuesday.equals(newHours.tuesday)
                && this.wednesday.equals(newHours.wednesday)
                && this.thursday.equals(newHours.thursday)
                && this.friday.equals(newHours.friday)
                && this.saturday.equals(newHours.saturday)
                && this.sunday.equals(newHours.sunday);
    }
}
