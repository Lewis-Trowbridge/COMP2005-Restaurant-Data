package com.restaurants.web;

import com.restaurants.models.Restaurant;

import java.util.ArrayList;

public interface IAPIAccessor {
    public ArrayList<Restaurant> getAllRestaurants();
}
