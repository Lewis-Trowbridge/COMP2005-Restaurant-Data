package com.restaurants.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurants.models.Restaurant;
import com.restaurants.models.Restaurants;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class APIAccessor implements IAPIAccessor {

    final private HttpClient client;
    final private ObjectMapper mapper;

    public APIAccessor() {
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        mapper = new ObjectMapper();
    }

    @Override
    public ArrayList<Restaurant> getAllRestaurants() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://intelligent-social-robots-ws.com/restaurant-data.json"))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Restaurants restaurants = mapper.readValue(response.body(), Restaurants.class);
            return restaurants.restaurants;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
