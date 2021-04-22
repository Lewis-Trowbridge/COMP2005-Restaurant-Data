package com;

import com.restaurants.filters.RestaurantFilters;
import com.restaurants.models.Restaurant;
import com.restaurants.web.APIAccessor;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        APIAccessor help = new APIAccessor();
        ArrayList<Restaurant> restaurants = help.getAllRestaurants();
        RestaurantFilters filters = new RestaurantFilters();

        filters.orderRestaurantsByDistanceFromHotel(restaurants);
    }
}
