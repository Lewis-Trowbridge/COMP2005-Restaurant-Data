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

    @Test
    public void filterRestaurantsByNeighbourhoodWithValidInputs() {
        String testNeighbourhood = "Queens";
        ArrayList<Restaurant> queensRestaurants = new ArrayList<>();
        queensRestaurants.add(restaurants.get(8));
        queensRestaurants.add(restaurants.get(9));

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);

        assertEquals(queensRestaurants, filteredRestaurants);
    }

    @Test
    public void filterRestaurantsByNeighbourhoodWithFakeNeighbourhood() {
        String testNeighbourhood = "Chelsea";
        ArrayList<Restaurant> emptyRestaurants = new ArrayList<>();

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);

        assertEquals(emptyRestaurants, filteredRestaurants);
    }

    @Test
    public void filterRestaurantsByNeighbourhoodWithEmptyString() {
        String testNeighbourhood = "";
        ArrayList<Restaurant> emptyRestaurants = new ArrayList<>();

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);

        assertEquals(emptyRestaurants, filteredRestaurants);
    }

    @Test
    public void filterRestaurantsByHourWithValidInputs() {
        int testHour = 10;
        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();
        expectedRestaurants.add(restaurants.get(3));
        expectedRestaurants.add(restaurants.get(4));
        expectedRestaurants.add(restaurants.get(7));

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByHour(restaurants, testHour);

        assertEquals(expectedRestaurants, filteredRestaurants);
    }

    @Test
    public void filterRestaurantsByHourWithHourZero() {
        int testHour = 0;
        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();
        expectedRestaurants.add(restaurants.get(0));
        expectedRestaurants.add(restaurants.get(2));
        expectedRestaurants.add(restaurants.get(3));
        expectedRestaurants.add(restaurants.get(4));
        expectedRestaurants.add(restaurants.get(9));

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByHour(restaurants, testHour);

        assertEquals(expectedRestaurants, filteredRestaurants);
    }

    @Test
    public void filterRestaurantsByHourWithHour24(){
        int testHour = 24;
        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();
        expectedRestaurants.add(restaurants.get(0));
        expectedRestaurants.add(restaurants.get(2));
        expectedRestaurants.add(restaurants.get(3));
        expectedRestaurants.add(restaurants.get(4));
        expectedRestaurants.add(restaurants.get(9));

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByHour(restaurants, testHour);

        assertEquals(expectedRestaurants, filteredRestaurants);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterRestaurantsByHourWithInvalidHour(){
        int testHour = 50;

        filters.filterRestaurantsByHour(restaurants, testHour);
    }

    @Test
    public void filterRestaurantsByDayOfWeekWithValidInputs() {
        ArrayList<Restaurant> expectedRestaurants = (ArrayList<Restaurant>) restaurants.clone();
        // Restaurant index 5 is the only restaurant not open on Monday, remove this to test filtering
        expectedRestaurants.remove(5);
        String testDay = "Monday";

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByDayOfWeek(restaurants, testDay);

        assertEquals(expectedRestaurants, filteredRestaurants);
    }

    @Test
    public void filterRestaurantsByDayOfWeekWithShortenedDay() {
        ArrayList<Restaurant> expectedRestaurants = (ArrayList<Restaurant>) restaurants.clone();
        // Restaurant index 5 is the only restaurant not open on Monday, remove this to test filtering
        expectedRestaurants.remove(5);
        String testDay = "Mon";

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByDayOfWeek(restaurants, testDay);

        assertEquals(expectedRestaurants, filteredRestaurants);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterRestaurantsByDayOfWeekWithNonexistentDay() {
        String testDay = "fidsuhfiduhiushfih";

        filters.filterRestaurantsByDayOfWeek(restaurants, testDay);
    }

    @Test
    public void filterRestaurantsByAverageReviewsWithValidInputs() {
        float testScore = 4.5f;
        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();

        expectedRestaurants.add(restaurants.get(9));

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByAverageReviews(restaurants, testScore);

        assertEquals(expectedRestaurants, filteredRestaurants);

    }

    @Test(expected = IllegalArgumentException.class)
    public void filterRestaurantsByAverageReviewsWithNegativeInput(){
        float testScore = -3f;

        filters.filterRestaurantsByAverageReviews(restaurants, testScore);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterRestaurantsByAverageReviewsWithTooHighInput(){
        float testScore = 10f;

        filters.filterRestaurantsByAverageReviews(restaurants, testScore);
    }

    @Test
    public void orderRestaurantsByDOHMHInspectionScoreWithValidInputs(){
        // Remove null score
        ArrayList<Restaurant> testRestaurants = (ArrayList<Restaurant>) restaurants.clone();
        testRestaurants.remove(2);

        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();
        // Construct array list manually in order
        expectedRestaurants.add(restaurants.get(5));
        expectedRestaurants.add(restaurants.get(4));
        expectedRestaurants.add(restaurants.get(3));
        expectedRestaurants.add(restaurants.get(9));
        expectedRestaurants.add(restaurants.get(0));
        expectedRestaurants.add(restaurants.get(7));
        expectedRestaurants.add(restaurants.get(8));
        expectedRestaurants.add(restaurants.get(1));
        expectedRestaurants.add(restaurants.get(6));

        ArrayList<Restaurant> orderedRestaurants = filters.orderRestaurantsByDOHMHInspectionScore(testRestaurants);

        assertEquals(expectedRestaurants, orderedRestaurants);

    }

    @Test(expected = NullPointerException.class)
    public void orderRestaurantsByDOHMHInspectionScoreWithNullScore(){
        ArrayList<Restaurant> testRestaurants = (ArrayList<Restaurant>) restaurants.clone();

        filters.orderRestaurantsByDOHMHInspectionScore(testRestaurants);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void orderRestaurantsByDOHMHInspectionScoreWithEmptyList(){
        ArrayList<Restaurant> testRestaurants = new ArrayList<>();

        filters.orderRestaurantsByDOHMHInspectionScore(testRestaurants);
    }

    @After
    public void tearDown() throws Exception {
    }
}