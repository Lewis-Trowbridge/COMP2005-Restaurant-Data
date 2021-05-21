package com.restaurants.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurants.models.Restaurant;
import com.restaurants.models.RestaurantsList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MockAPIAccessor implements IAPIAccessor {

    ObjectMapper mapper;

    public MockAPIAccessor() {
        mapper = new ObjectMapper();
    }

    @Override
    public ArrayList<Restaurant> getAllRestaurants() {
        try {
            RestaurantsList restaurantsList = mapper.readValue(new File("./src/test/resources/restaurant-data.json"), RestaurantsList.class);
            return restaurantsList.restaurants;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
