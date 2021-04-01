package com.restaurants.filters;

import com.restaurants.models.Restaurant;

import java.util.ArrayList;

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
        return null;
    }

}
