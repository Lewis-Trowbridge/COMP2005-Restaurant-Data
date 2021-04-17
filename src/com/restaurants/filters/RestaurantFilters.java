package com.restaurants.filters;

import com.restaurants.models.OpeningHours;
import com.restaurants.models.Restaurant;
import com.restaurants.models.Review;

import java.util.ArrayList;
import java.util.Comparator;

public class RestaurantFilters {

    public ArrayList<Restaurant> filterRestaurantsByCuisine(ArrayList<Restaurant> restaurants, String cuisine){
        ArrayList<Restaurant> restaurantsCopy = new ArrayList<>();

        for (Restaurant currentRestaurant: restaurants) {
            if (currentRestaurant.getCuisineType().equals(cuisine)){
                restaurantsCopy.add(currentRestaurant);
            }
        }

        return restaurantsCopy;
    }

    public ArrayList<Restaurant> filterRestaurantsByNeighbourhood(ArrayList<Restaurant> restaurants, String neighbourhood){
        ArrayList<Restaurant> restaurantsCopy = new ArrayList<>();

        for (Restaurant currentRestaurant: restaurants) {
            if (currentRestaurant.getNeighbourhood().equals(neighbourhood)){
                restaurantsCopy.add(currentRestaurant);
            }
        }

        return restaurantsCopy;
    }

    public ArrayList<Restaurant> filterRestaurantsByHour(ArrayList<Restaurant> restaurants, int hour) throws IllegalArgumentException {
        return null;
    }

    public ArrayList<Restaurant> filterRestaurantsByDayOfWeek(ArrayList<Restaurant> restaurants, String dayOfWeek) throws IllegalArgumentException{
        ArrayList<Restaurant> restaurantsCopy = new ArrayList<>();

        for (Restaurant currentRestaurant: restaurants) {
            OpeningHours hours = currentRestaurant.getOpeningHours();
            switch (dayOfWeek){
                case "Monday":
                case "Mon":
                    if (hours.getMondayOpen()[0] != null && hours.getMondayClose()[0] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                    break;
                case "Tuesday":
                case "Tue":
                    if (hours.getTuesdayOpen()[0] != null && hours.getTuesdayClose()[0] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                case "Wednesday":
                case "Wed":
                    if (hours.getWednesdayOpen()[0] != null && hours.getWednesdayClose()[0] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                case "Thursday":
                case "Thurs":
                    if (hours.getThursdayOpen()[0] != null && hours.getThursdayClose()[0] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                case "Friday":
                case "Fri":
                    if (hours.getFridayOpen()[0] != null && hours.getFridayClose()[0] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                case "Saturday":
                case "Sat":
                    if (hours.getSaturdayOpen()[0] != null && hours.getSaturdayClose()[0] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                case "Sunday":
                case "Sun":
                    if (hours.getSundayOpen()[0] != null && hours.getSundayClose()[0] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                default:
                    throw new IllegalArgumentException();
            }
        }

        return restaurantsCopy;
    }

    public ArrayList<Restaurant> filterRestaurantsByAverageReviews(ArrayList<Restaurant> restaurants, float averageScore) throws IllegalArgumentException {
        ArrayList<Restaurant> restaurantsCopy = new ArrayList<>();

        if (averageScore >= 0 && averageScore <= 5){

            for (Restaurant currentRestaurant: restaurants) {
                float totalScore = 0;
                Review[] reviews = currentRestaurant.getReviews();
                for (Review currentReview: reviews){
                    totalScore += currentReview.getRating();
                }
                if (totalScore / reviews.length >= averageScore) {
                    restaurantsCopy.add(currentRestaurant);
                }
            }
            return restaurantsCopy;
        }

        else {
            throw new IllegalArgumentException();
        }

    }

    public ArrayList<Restaurant> orderRestaurantsByDOHMHInspectionScore(ArrayList<Restaurant> restaurants) throws IndexOutOfBoundsException{

        if (restaurants.size() > 0){
            ArrayList<Restaurant> restaurantsCopy = (ArrayList<Restaurant>) restaurants.clone();
            restaurantsCopy.sort(
                    Comparator.comparing(Restaurant::getDOHMHInspectionScore).reversed()
            );

            return restaurantsCopy;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }
}
