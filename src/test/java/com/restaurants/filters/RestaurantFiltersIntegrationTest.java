package com.restaurants.filters;

import com.restaurants.models.Restaurant;
import com.restaurants.web.APIAccessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RestaurantFiltersIntegrationTest {

    APIAccessor apiAccessor;
    ArrayList<Restaurant> restaurants;
    RestaurantFilters filters;

    @Before
    public void setUp() {
        apiAccessor = new APIAccessor();
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

    @Test(expected = NullPointerException.class)
    public void filterRestaurantsByCuisineWithNullCuisine() {
        String testCuisine = "Mexican";
        restaurants.get(0).setCuisineType(null);

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByCuisine(restaurants, testCuisine);
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

    @Test(expected = NullPointerException.class)
    public void filterRestaurantsByNeighbourhoodWithNullNeighbourhood() {
        String testNeighbourhood = "Queens";
        restaurants.get(0).setNeighbourhood(null);

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
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
        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>(restaurants);
        // Restaurant index 5 is the only restaurant not open on Monday, remove this to test filtering
        expectedRestaurants.remove(5);
        String testDay = "Monday";

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByDayOfWeek(restaurants, testDay);

        assertEquals(expectedRestaurants, filteredRestaurants);
    }

    @Test
    public void filterRestaurantsByDayOfWeekWithShortenedDay() {
        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>(restaurants);
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
        ArrayList<Restaurant> testRestaurants = new ArrayList<>(restaurants);
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
        ArrayList<Restaurant> testRestaurants = new ArrayList<>(restaurants);

        filters.orderRestaurantsByDOHMHInspectionScore(testRestaurants);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void orderRestaurantsByDOHMHInspectionScoreWithEmptyList(){
        ArrayList<Restaurant> testRestaurants = new ArrayList<>();

        filters.orderRestaurantsByDOHMHInspectionScore(testRestaurants);
    }

    @Test
    public void orderRestaurantsByDistanceFromHotelWithValidInputs(){
        ArrayList<Restaurant> testRestaurants = new ArrayList<>();
        testRestaurants.add(restaurants.get(2));
        testRestaurants.add(restaurants.get(8));
        testRestaurants.add(restaurants.get(9));
        testRestaurants.add(restaurants.get(1));
        testRestaurants.add(restaurants.get(6));
        testRestaurants.add(restaurants.get(3));
        testRestaurants.add(restaurants.get(7));
        testRestaurants.add(restaurants.get(5));
        testRestaurants.add(restaurants.get(0));
        testRestaurants.add(restaurants.get(4));


        ArrayList<Restaurant> orderedRestaurants = filters.orderRestaurantsByDistanceFromHotel(restaurants);

        assertEquals(testRestaurants, orderedRestaurants);
    }

    @Test(expected = NullPointerException.class)
    public void orderRestaurantsByDistanceFromHotelWithNullNeighbourhood(){
        // Set restaurant neighbourhood to null
        restaurants.get(0).setNeighbourhood(null);

        filters.orderRestaurantsByDistanceFromHotel(restaurants);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void orderRestaurantsByDistanceFromHotelWithEmptyList(){
        ArrayList<Restaurant> emptyRestaurants = new ArrayList<>();

        filters.orderRestaurantsByDistanceFromHotel(emptyRestaurants);
    }

    @Test
    public void filterRestaurantsByCuisineAndNeighbourhoodWithValidInputs(){
        String testCuisine = "Asian";
        String testNeighbourhood = "Manhattan";

        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();
        expectedRestaurants.add(restaurants.get(0));
        expectedRestaurants.add(restaurants.get(2));

        ArrayList<Restaurant> testRestaurants = filters.filterRestaurantsByCuisine(restaurants, testCuisine);
        testRestaurants = filters.filterRestaurantsByNeighbourhood(testRestaurants, testNeighbourhood);

        assertEquals(expectedRestaurants, testRestaurants);
    }

    @Test
    public void filterRestaurantsByCuisineAndNeighbourhoodWithFakeNeighbourhood(){
        String testCuisine = "Asian";
        String testNeighbourhood = "Chelsea";

        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();

        ArrayList<Restaurant> testRestaurants = filters.filterRestaurantsByCuisine(restaurants, testCuisine);
        testRestaurants = filters.filterRestaurantsByNeighbourhood(testRestaurants, testNeighbourhood);

        assertEquals(expectedRestaurants, testRestaurants);
    }

    @Test
    public void filterRestaurantsByCuisineAndNeighbourhoodWithFakeCuisine(){
        String testCuisine = "Thai";
        String testNeighbourhood = "Queens";

        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();

        ArrayList<Restaurant> testRestaurants = filters.filterRestaurantsByCuisine(restaurants, testCuisine);
        testRestaurants = filters.filterRestaurantsByNeighbourhood(testRestaurants, testNeighbourhood);

        assertEquals(expectedRestaurants, testRestaurants);
    }

    @Test(expected = NullPointerException.class)
    public void filterRestaurantsByCuisineAndNeighbourhoodWithNullCuisine(){
        String testCuisine = "Asian";
        String testNeighbourhood = "Manhattan";
        restaurants.get(0).setCuisineType(null);

        ArrayList<Restaurant> testRestaurants =  filters.filterRestaurantsByCuisine(restaurants, testCuisine);
        testRestaurants = filters.filterRestaurantsByNeighbourhood(testRestaurants, testNeighbourhood);
    }

    @Test(expected = NullPointerException.class)
    public void filterRestaurantsByCuisineAndNeighbourhoodWithNullNeighbourhood(){
        String testCuisine = "Asian";
        String testNeighbourhood = "Manhattan";
        restaurants.get(0).setNeighbourhood(null);

        ArrayList<Restaurant> testRestaurants =  filters.filterRestaurantsByCuisine(restaurants, testCuisine);
        testRestaurants = filters.filterRestaurantsByNeighbourhood(testRestaurants, testNeighbourhood);
    }
    
    @Test
    public void filterRestaurantsByDayOfWeekAndHourWithValidInputs(){
        String testDayOfWeek = "Sun";
        int testHour = 12;
        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>(restaurants);
        expectedRestaurants.remove(8);

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByDayOfWeek(restaurants, testDayOfWeek);
        filteredRestaurants = filters.filterRestaurantsByHour(filteredRestaurants, testHour);

        assertEquals(expectedRestaurants, filteredRestaurants);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterRestaurantsByDayOfWeekAndHourWithInvalidDayOfWeek(){
        String testDayOfWeek = "u09uewhdhiofhsiodhfj";
        int testHour = 12;

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByDayOfWeek(restaurants, testDayOfWeek);
        filteredRestaurants = filters.filterRestaurantsByHour(filteredRestaurants, testHour);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterRestaurantsByDayOfWeekAndHourWithInvalidHour(){
        String testDayOfWeek = "Wed";
        int testHour = 50;

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByDayOfWeek(restaurants, testDayOfWeek);
        filteredRestaurants = filters.filterRestaurantsByHour(filteredRestaurants, testHour);
    }

    @Test
    public void filterRestaurantsByNeighbourhoodAndReviewScoresWithValidInputs(){
        String testNeighbourhood = "Manhattan";
        float testScore = 4.0f;
        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();
        expectedRestaurants.add(restaurants.get(3));
        expectedRestaurants.add(restaurants.get(6));
        expectedRestaurants.add(restaurants.get(7));

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
        filteredRestaurants = filters.filterRestaurantsByAverageReviews(filteredRestaurants, testScore);

        assertEquals(expectedRestaurants, filteredRestaurants);
    }

    @Test
    public void filterRestaurantsByNeighbourhoodAndReviewScoresWithFakeNeighbourhood(){
        String testNeighbourhood = "Chelsea";
        float testScore = 4.0f;
        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
        filteredRestaurants = filters.filterRestaurantsByAverageReviews(filteredRestaurants, testScore);

        assertEquals(expectedRestaurants, filteredRestaurants);
    }

    @Test(expected = NullPointerException.class)
    public void filterRestaurantsNeighbourhoodAndReviewScoresWithNullNeighbourhood(){
        String testNeighbourhood = "Queens";
        float testScore = 3.0f;

        restaurants.get(0).setNeighbourhood(null);

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
        filteredRestaurants = filters.filterRestaurantsByAverageReviews(filteredRestaurants, testScore);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterRestaurantsByNeighbourhoodAndReviewScoresWithNegativeScore(){
        String testNeighbourhood = "Manhattan";
        float testScore = -5.0f;

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
        filteredRestaurants = filters.filterRestaurantsByAverageReviews(filteredRestaurants, testScore);
    }

    @Test(expected = IllegalArgumentException.class)
    public void filterRestaurantsByNeighbourhoodAndReviewScoresWithTooHighScore(){
        String testNeighbourhood = "Manhattan";
        float testScore = 1000.0f;

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
        filteredRestaurants = filters.filterRestaurantsByAverageReviews(filteredRestaurants, testScore);
    }

    @Test
    public void filterRestaurantsByNeighbourhoodAndOrderByInspectionScoreWithValidInputs(){
        String testNeighbourhood = "Brooklyn";
        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();
        expectedRestaurants.add(restaurants.get(5));
        expectedRestaurants.add(restaurants.get(4));
        expectedRestaurants.add(restaurants.get(1));

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
        filteredRestaurants = filters.orderRestaurantsByDOHMHInspectionScore(filteredRestaurants);

        assertEquals(expectedRestaurants, filteredRestaurants);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void filterRestaurantsByNeighbourhoodAndOrderByInspectionScoreWithFakeNeighbourhood(){
        String testNeighbourhood = "Chelsea";
        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
        filteredRestaurants = filters.orderRestaurantsByDOHMHInspectionScore(filteredRestaurants);
    }

    @Test(expected = NullPointerException.class)
    public void filterRestaurantsByNeighbourhoodAndOrderByInspectionScoreWithNullNeighbourhood(){
        String testNeighbourhood = "Brooklyn";

        restaurants.get(0).setNeighbourhood(null);

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
        filteredRestaurants = filters.orderRestaurantsByDOHMHInspectionScore(filteredRestaurants);
    }
    
    @Test
    public void filterRestaurantsByNeighbourhoodAndDistanceFromHotelWithValidInputs(){
        String testNeighbourhood = "Brooklyn";

        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();
        expectedRestaurants.add(restaurants.get(1));
        expectedRestaurants.add(restaurants.get(5));
        expectedRestaurants.add(restaurants.get(4));


        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
        filteredRestaurants = filters.orderRestaurantsByDistanceFromHotel(filteredRestaurants);

        assertEquals(expectedRestaurants, filteredRestaurants);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void filterRestaurantsByNeighbourhoodAndOrderByDistanceFromHotelWithFakeNeighbourhood(){
        String testNeighbourhood = "Chelsea";

        ArrayList<Restaurant> expectedRestaurants = new ArrayList<>();

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
        filteredRestaurants = filters.orderRestaurantsByDistanceFromHotel(filteredRestaurants);
    }

    @Test(expected = NullPointerException.class)
    public void filterRestaurantsByNeighbourhoodAndOrderByDistanceFromHotelWithNullNeighbourhood(){
        String testNeighbourhood = "Brooklyn";

        restaurants.get(0).setNeighbourhood(null);

        ArrayList<Restaurant> filteredRestaurants = filters.filterRestaurantsByNeighbourhood(restaurants, testNeighbourhood);
        filteredRestaurants = filters.orderRestaurantsByDistanceFromHotel(filteredRestaurants);
    }

    @After
    public void tearDown() {
    }
}