package com.restaurants.filters;

import com.restaurants.models.LatLngCoordinates;
import com.restaurants.models.OpeningHours;
import com.restaurants.models.Restaurant;
import com.restaurants.models.Review;

import java.util.ArrayList;
import java.util.Comparator;
import java.lang.Math;

public class RestaurantFilters {

    private static final LatLngCoordinates manhattanHotel = new LatLngCoordinates(40.752831, -73.985748);
    private static final LatLngCoordinates queensHotel = new LatLngCoordinates(40.753990, -73.949240);
    private static final LatLngCoordinates brooklynHotel = new LatLngCoordinates(40.689510, -73.988100);
    private static final String manhattanName = "Manhattan";
    private static final String queensName = "Queens";
    private static final String brooklynName = "Brooklyn";

    public ArrayList<Restaurant> filterRestaurantsByCuisine(ArrayList<Restaurant> restaurants, String cuisine) {
        ArrayList<Restaurant> restaurantsCopy = new ArrayList<>();

        for (Restaurant currentRestaurant: restaurants) {
            if (currentRestaurant.getCuisineType().equals(cuisine)){
                restaurantsCopy.add(currentRestaurant);
            }
        }

        return restaurantsCopy;
    }

    public ArrayList<Restaurant> filterRestaurantsByNeighbourhood(ArrayList<Restaurant> restaurants, String neighbourhood) {
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

    public ArrayList<Restaurant> filterRestaurantsByDayOfWeek(ArrayList<Restaurant> restaurants, String dayOfWeek) throws IllegalArgumentException {
        ArrayList<Restaurant> restaurantsCopy = new ArrayList<>();

        for (Restaurant currentRestaurant: restaurants) {
            OpeningHours hours = currentRestaurant.getOpeningHours();
            switch (dayOfWeek){
                case "Monday":
                case "Mon":
                    if (hours.getMonday()[0][0] != null && hours.getMonday()[0][1] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                    break;
                case "Tuesday":
                case "Tue":
                    if (hours.getTuesday()[0][0] != null && hours.getTuesday()[0][1] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                case "Wednesday":
                case "Wed":
                    if (hours.getWednesday()[0][0] != null && hours.getWednesday()[0][1] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                case "Thursday":
                case "Thurs":
                    if (hours.getThursday()[0][0] != null && hours.getThursday()[0][1] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                case "Friday":
                case "Fri":
                    if (hours.getFriday()[0][0] != null && hours.getFriday()[0][1] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                case "Saturday":
                case "Sat":
                    if (hours.getSaturday()[0][0] != null && hours.getSaturday()[0][1] != null){
                        restaurantsCopy.add(currentRestaurant);
                    }
                case "Sunday":
                case "Sun":
                    if (hours.getSunday()[0][0] != null && hours.getSunday()[0][1] != null){
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

    public ArrayList<Restaurant> orderRestaurantsByDOHMHInspectionScore(ArrayList<Restaurant> restaurants) throws IndexOutOfBoundsException {

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

    public ArrayList<Restaurant> orderRestaurantsByDistanceFromHotel(ArrayList<Restaurant> restaurants) throws IndexOutOfBoundsException, NullPointerException {
        ArrayList<RestaurantDistance> distances = new ArrayList<>();
        for (Restaurant currentRestaurant: restaurants) {
            switch (currentRestaurant.getNeighbourhood()){
                case manhattanName:
                    distances.add(new RestaurantDistance(currentRestaurant, GetDistanceBetweenTwoCoords(currentRestaurant.getLatlng(), manhattanHotel)));
                    break;
                case queensName:
                    distances.add(new RestaurantDistance(currentRestaurant, GetDistanceBetweenTwoCoords(currentRestaurant.getLatlng(), queensHotel)));
                    break;
                case brooklynName:
                    distances.add(new RestaurantDistance(currentRestaurant, GetDistanceBetweenTwoCoords(currentRestaurant.getLatlng(), brooklynHotel)));
                    break;
            }
        }

        distances.sort(
                Comparator.comparing(RestaurantDistance::getDistance)
        );

        ArrayList<Restaurant> restaurantsCopy = new ArrayList<>();

        for (RestaurantDistance currentRestaurant: distances) {
            restaurantsCopy.add(currentRestaurant.getRestaurant());
        }

        return restaurantsCopy;
    }

    private double GetDistanceBetweenTwoCoords(LatLngCoordinates coords1, LatLngCoordinates coords2){
        return Math.sqrt(Math.pow((coords1.getLatitude() - coords2.getLatitude()), 2) + Math.pow((coords1.getLongitude() - coords2.getLongitude()), 2));
    }
}
