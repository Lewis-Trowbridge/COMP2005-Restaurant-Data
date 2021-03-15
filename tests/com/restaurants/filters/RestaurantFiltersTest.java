package com.restaurants.filters;

import com.restaurants.models.Restaurant;
import com.restaurants.web.MockAPIAccessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RestaurantFiltersTest {

    MockAPIAccessor apiAccessor;
    ArrayList<Restaurant> restaurants;
    RestaurantFilters filters;

    @Before
    public void setUp() throws Exception {
        apiAccessor = new MockAPIAccessor();
        restaurants = apiAccessor.getAllRestaurants();
        filters = new RestaurantFilters();
    }

    @Test
    public void filterRestaurantsByCuisineWithValidInputs() {

        String testCuisine = "Mexican";
        ArrayList<Restaurant> mexicanRestaurantList = new ArrayList<>();
        mexicanRestaurantList.add(restaurants.get(9));



        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByCuisine(restaurants, testCuisine);

        assertEquals(mexicanRestaurantList, filteredRestaurants);

    }

    @Test
    public void filterRestaurantsByCuisineWithFakeCuisine() {
        String testCuisine = "Thai";
        ArrayList<Restaurant> emptyRestaurants = new ArrayList<>();

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByCuisine(restaurants, testCuisine);

        assertEquals(emptyRestaurants, filteredRestaurants);
    }

    @Test
    public void filterRestaurantsByCuisineWithEmptyString() {
        String testCuisine = "";
        ArrayList<Restaurant> emptyRestaurants = new ArrayList<>();

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByCuisine(restaurants, testCuisine);

        assertEquals(emptyRestaurants, filteredRestaurants);
    }

    @After
    public void tearDown() throws Exception {
    }
}