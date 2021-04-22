package com.restaurants.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {
    private String name;
    private String date;
    private int rating;
    private String comments;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("rating")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if (!(obj instanceof Review)){
            return false;
        }

        Review newReview = (Review)obj;

        return this.name.equals(newReview.name)
                && this.date.equals(newReview.date)
                && this.rating == newReview.rating
                && this.comments.equals(newReview.comments);
    }
}
