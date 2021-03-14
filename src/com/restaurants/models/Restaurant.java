package com.restaurants.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Restaurant {
    private int id;
    private String name;
    private String DOMHInspectionScore;
    private String neighbourhood;
    private String photograph;
    private String address;
    private LatLngCoordinates latlng;
    private String cuisineType;
    private OpeningHours openingHours;
    private Review[] reviews;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("DOHMH_inspection_score")
    public String getDOMHInspectionScore() {
        return DOMHInspectionScore;
    }

    public void setDOMHInspectionScore(String DOMHInspectionScore) {
        this.DOMHInspectionScore = DOMHInspectionScore;
    }

    @JsonProperty("neighborhood")
    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    @JsonProperty("photograph")
    public String getPhotograph() {
        return photograph;
    }

    public void setPhotograph(String photograph) {
        this.photograph = photograph;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("latlng")
    public LatLngCoordinates getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLngCoordinates latlng) {
        this.latlng = latlng;
    }

    @JsonProperty("cuisine_type")
    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    @JsonProperty("operating_hours")
    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    @JsonProperty("reviews")
    public Review[] getReviews() {
        return reviews;
    }

    public void setReviews(Review[] reviews) {
        this.reviews = reviews;
    }
}
