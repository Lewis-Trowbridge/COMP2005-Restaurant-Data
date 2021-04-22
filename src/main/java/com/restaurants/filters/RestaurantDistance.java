package com.restaurants.filters;

import com.restaurants.models.Restaurant;

public class RestaurantDistance {
    private Restaurant restaurant;
    private Double distance;

    public RestaurantDistance(Restaurant restaurant, Double distance){
        this.restaurant = restaurant;
        this.distance = distance;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
