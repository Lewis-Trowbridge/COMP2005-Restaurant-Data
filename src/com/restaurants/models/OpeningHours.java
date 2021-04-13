package com.restaurants.models;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class OpeningHours {
    private LocalTime[] mondayOpen;
    private LocalTime[] mondayClose;
    private LocalTime[] tuesdayOpen;
    private LocalTime[] tuesdayClose;
    private LocalTime[] wednesdayOpen;
    private LocalTime[] wednesdayClose;
    private LocalTime[] thursdayOpen;
    private LocalTime[] thursdayClose;
    private LocalTime[] fridayOpen;
    private LocalTime[] fridayClose;
    private LocalTime[] saturdayOpen;
    private LocalTime[] saturdayClose;
    private LocalTime[] sundayOpen;
    private LocalTime[] sundayClose;

    public LocalTime[] getMondayOpen() {
        return mondayOpen;
    }

    public LocalTime[] getMondayClose() {
        return mondayClose;
    }

    @JsonSetter("Monday")
    public void setMonday(String mondayString) {
        LocalTime[] fullDay = checkFullDay(mondayString);
        if (!(fullDay == null)){
            this.mondayOpen = new LocalTime[]{fullDay[0]};
            this.mondayClose = new LocalTime[]{fullDay[1]};
        }
        else {
            this.mondayOpen = getOpeningTimes(mondayString);
            this.mondayClose = getClosingTimes(mondayString);
        }
    }

    public LocalTime[] getTuesdayOpen() {
        return tuesdayOpen;
    }

    public LocalTime[] getTuesdayClose() {
        return tuesdayClose;
    }

    @JsonSetter("Tuesday")
    public void setTuesday(String tuesdayString) {
        LocalTime[] fullDay = checkFullDay(tuesdayString);
        if (!(fullDay == null)){
            this.tuesdayOpen = new LocalTime[]{fullDay[0]};
            this.tuesdayClose = new LocalTime[]{fullDay[1]};
        }
        else {
            this.tuesdayOpen = getOpeningTimes(tuesdayString);
            this.tuesdayClose = getClosingTimes(tuesdayString);
        }
    }

    public LocalTime[] getWednesdayOpen() {
        return wednesdayOpen;
    }

    public LocalTime[] getWednesdayClose() {
        return wednesdayClose;
    }

    @JsonSetter("Wednesday")
    public void setWednesday(String wednesdayString) {
        LocalTime[] fullDay = checkFullDay(wednesdayString);
        if (!(fullDay == null)){
            this.wednesdayOpen = new LocalTime[]{fullDay[0]};
            this.wednesdayClose = new LocalTime[]{fullDay[1]};
        }
        else {
            this.wednesdayOpen = getOpeningTimes(wednesdayString);
            this.wednesdayClose = getClosingTimes(wednesdayString);
        }
    }

    public LocalTime[] getThursdayOpen() {
        return thursdayOpen;
    }

    public LocalTime[] getThursdayClose() {
        return thursdayClose;
    }

    @JsonSetter("Thursday")
    public void setThursday(String thursdayString) {
        LocalTime[] fullDay = checkFullDay(thursdayString);
        if (!(fullDay == null)){
            this.thursdayOpen = new LocalTime[]{fullDay[0]};
            this.thursdayClose = new LocalTime[]{fullDay[1]};
        }
        else {
            this.thursdayOpen = getOpeningTimes(thursdayString);
            this.thursdayClose = getClosingTimes(thursdayString);
        }
    }

    public LocalTime[] getFridayOpen() {
        return fridayOpen;
    }

    public LocalTime[] getFridayClose() {
        return fridayClose;
    }

    @JsonSetter("Friday")
    public void setFriday(String fridayString) {
        LocalTime[] fullDay = checkFullDay(fridayString);
        if (!(fullDay == null)){
            this.fridayOpen = new LocalTime[]{fullDay[0]};
            this.fridayClose = new LocalTime[]{fullDay[1]};
        }
        else {
            this.fridayOpen = getOpeningTimes(fridayString);
            this.fridayClose = getClosingTimes(fridayString);
        }
    }

    public LocalTime[] getSaturdayOpen() {
        return saturdayOpen;
    }

    public LocalTime[] getSaturdayClose() {
        return saturdayClose;
    }

    @JsonSetter("Saturday")
    public void setSaturday(String saturdayString) {
        LocalTime[] fullDay = checkFullDay(saturdayString);
        if (!(fullDay == null)){
            this.saturdayOpen = new LocalTime[]{fullDay[0]};
            this.saturdayClose = new LocalTime[]{fullDay[1]};
        }
        else {
            this.saturdayOpen = getOpeningTimes(saturdayString);
            this.saturdayClose = getClosingTimes(saturdayString);
        }
    }

    public LocalTime[] getSundayOpen() {
        return sundayOpen;
    }

    public LocalTime[] getSundayClose() {
        return sundayClose;
    }

    @JsonSetter("Sunday")
    public void setSunday(String sundayString) {
        LocalTime[] fullDay = checkFullDay(sundayString);
        if (!(fullDay == null)){
            this.sundayOpen = new LocalTime[]{fullDay[0]};
            this.sundayClose = new LocalTime[]{fullDay[1]};
        }
        else {
            this.sundayOpen = getOpeningTimes(sundayString);
            this.sundayClose = getClosingTimes(sundayString);
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

        return Arrays.equals(this.mondayOpen, newHours.mondayOpen)
                && Arrays.equals(this.mondayClose, newHours.mondayClose)
                && Arrays.equals(this.tuesdayOpen, newHours.tuesdayOpen)
                && Arrays.equals(this.tuesdayClose, newHours.tuesdayClose)
                && Arrays.equals(this.wednesdayOpen, newHours.wednesdayOpen)
                && Arrays.equals(this.wednesdayClose, newHours.wednesdayClose)
                && Arrays.equals(this.thursdayOpen, newHours.thursdayOpen)
                && Arrays.equals(this.thursdayClose, newHours.thursdayClose)
                && Arrays.equals(this.fridayOpen, newHours.fridayOpen)
                && Arrays.equals(this.fridayClose, newHours.fridayClose)
                && Arrays.equals(this.saturdayOpen, newHours.saturdayOpen)
                && Arrays.equals(this.saturdayClose, newHours.saturdayClose)
                && Arrays.equals(this.sundayOpen, newHours.sundayOpen)
                && Arrays.equals(this.sundayClose, newHours.sundayClose);
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

    private static LocalTime[] getOpeningTimes(String allTimeString){
        ArrayList<LocalTime> openingTimes = new ArrayList<>();
        String[] timeStrings = allTimeString.split(",");
        for (String timeString: timeStrings) {
            openingTimes.add(getOpeningTime(timeString));
        }
        return openingTimes.toArray(new LocalTime[0]);
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
