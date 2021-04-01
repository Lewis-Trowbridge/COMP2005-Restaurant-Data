package com.restaurants.web;

import com.restaurants.models.Restaurant;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class APIAccessorTest {

    APIAccessor realAccessor;
    MockAPIAccessor mockAccessor;

    @Before
    public void setUp() throws Exception {
        realAccessor = new APIAccessor();
        mockAccessor = new MockAPIAccessor();
    }

    @Test
    public void getAllRestaurantsGetsExpectedInformation(){
        // Arrange
        ArrayList<Restaurant> testRestaurants = mockAccessor.getAllRestaurants();

        // Act
        ArrayList<Restaurant> realRestaurants = realAccessor.getAllRestaurants();

        // Assert
        assertEquals(testRestaurants, realRestaurants);
    }
}