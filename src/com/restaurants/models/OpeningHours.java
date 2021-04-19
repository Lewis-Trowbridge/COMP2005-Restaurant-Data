package com.restaurants.models;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class OpeningHours {
    private LocalTime[][] monday;
    private LocalTime[][] tuesday;
    private LocalTime[][] wednesday;
    private LocalTime[][] thursday;
    private LocalTime[][] friday;
    private LocalTime[][] saturday;
    private LocalTime[][] sunday;

    public LocalTime[][] getMonday() {
        return monday;
    }

    @JsonSetter("Monday")
    public void setMonday(String mondayString) {
        LocalTime[] fullDay = checkFullDay(mondayString);
        if (!(fullDay == null)){
            this.monday = new LocalTime[][]{new LocalTime[]{fullDay[0], fullDay[1]}};
        }
        else {
            this.monday = getOpeningTimes(mondayString);
        }
    }

    public LocalTime[][] getTuesday() {
        return tuesday;
    }

    @JsonSetter("Tuesday")
    public void setTuesday(String tuesdayString) {
        LocalTime[] fullDay = checkFullDay(tuesdayString);
        if (!(fullDay == null)){
            this.tuesday = new LocalTime[][]{new LocalTime[]{fullDay[0], fullDay[1]}};
        }
        else {
            this.tuesday = getOpeningTimes(tuesdayString);
        }
    }

    public LocalTime[][] getWednesday() {
        return wednesday;
    }

    @JsonSetter("Wednesday")
    public void setWednesday(String wednesdayString) {
        LocalTime[] fullDay = checkFullDay(wednesdayString);
        if (!(fullDay == null)){
            this.wednesday = new LocalTime[][]{new LocalTime[]{fullDay[0], fullDay[1]}};
        }
        else {
            this.wednesday = getOpeningTimes(wednesdayString);
        }
    }

    public LocalTime[][] getThursday() {
        return thursday;
    }

    @JsonSetter("Thursday")
    public void setThursday(String thursdayString) {
        LocalTime[] fullDay = checkFullDay(thursdayString);
        if (!(fullDay == null)){
            this.thursday = new LocalTime[][]{new LocalTime[]{fullDay[0], fullDay[1]}};
        }
        else {
            this.thursday = getOpeningTimes(thursdayString);
        }
    }

    public LocalTime[][] getFriday() {
        return friday;
    }

    @JsonSetter("Friday")
    public void setFriday(String fridayString) {
        LocalTime[] fullDay = checkFullDay(fridayString);
        if (!(fullDay == null)){
            this.friday = new LocalTime[][]{new LocalTime[]{fullDay[0], fullDay[1]}};
        }
        else {
            this.friday = getOpeningTimes(fridayString);
        }
    }

    public LocalTime[][] getSaturday() {
        return saturday;
    }

    @JsonSetter("Saturday")
    public void setSaturday(String saturdayString) {
        LocalTime[] fullDay = checkFullDay(saturdayString);
        if (!(fullDay == null)){
            this.saturday = new LocalTime[][]{new LocalTime[]{fullDay[0], fullDay[1]}};
        }
        else {
            this.saturday = getOpeningTimes(saturdayString);
        }
    }

    public LocalTime[][] getSunday() {
        return sunday;
    }

    @JsonSetter("Sunday")
    public void setSunday(String sundayString) {
        LocalTime[] fullDay = checkFullDay(sundayString);
        if (!(fullDay == null)){
            this.sunday = new LocalTime[][]{new LocalTime[]{fullDay[0], fullDay[1]}};
        }
        else {
            this.sunday = getOpeningTimes(sundayString);
        }
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

        return Arrays.deepEquals(this.monday, newHours.monday)
                && Arrays.deepEquals(this.tuesday, newHours.tuesday)
                && Arrays.deepEquals(this.wednesday, newHours.wednesday)
                && Arrays.deepEquals(this.thursday, newHours.thursday)
                && Arrays.deepEquals(this.friday, newHours.friday)
                && Arrays.deepEquals(this.saturday, newHours.saturday)
                && Arrays.deepEquals(this.sunday, newHours.sunday);
    }

    private static LocalTime[] checkFullDay(String timeString){
        LocalTime[] timeArray;
        switch (timeString){
            case "Closed":
                timeArray = new LocalTime[]{null, null};
                return timeArray;
            case "Open 24 hours":
                timeArray = new LocalTime[]{LocalTime.MIN, LocalTime.MAX};
                return timeArray;
            default:
                return null;
        }
    }

    private static LocalTime[][] getOpeningTimes(String allTimeString){
        ArrayList<LocalTime[]> openingTimes = new ArrayList<>();
        String[] timeStrings = allTimeString.split(",");
        for (String timeString: timeStrings) {
            openingTimes.add(new LocalTime[]{getOpeningTime(timeString), getClosingTime(allTimeString)});
        }
        return openingTimes.toArray(new LocalTime[0][0]);
    }

    private static LocalTime[] getClosingTimes(String allTimeString){
        ArrayList<LocalTime> closingTimes = new ArrayList<>();
        String[] timeStrings = allTimeString.split(",");
        for (String timeString: timeStrings) {
            closingTimes.add(getClosingTime(timeString));
        }
        return closingTimes.toArray(new LocalTime[0]);
    }

    private static LocalTime getOpeningTime(String timeString){
        String timeSubstring = timeString.split("-")[0].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E");
            // Check if the string is a day of the week
            formatter.parse(timeSubstring.substring(0, 3));
            // If that didn't throw an exception, then this is likely the previous day, meaning we open
            // at the beginning of the day (midnight)
            return LocalTime.MIN;
        }
        catch (DateTimeParseException e) {
            return getTimeFromString(timeSubstring);
        }
    }

    private static LocalTime getClosingTime(String timeString){
        String timeSubstring = timeString.split("-")[1].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E");
            // Check if the string is a day of the week
            formatter.parse(timeSubstring);
            // If that didn't throw an exception, then this is likely the next day, meaning we stay open
            // to the end of the day (midnight)
            return LocalTime.MAX;
        }
        catch (DateTimeParseException e){
            return getTimeFromString(timeSubstring);
        }
    }

    private static LocalTime getTimeFromString(String timeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:m a");
        try {
            return LocalTime.parse(timeString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
