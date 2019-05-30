package com.anblak.placesapp.models;


import java.util.List;

public class Place {

    private Long id;
    private String name;
    private String description;
    private Double avgRating;
    private Long numberOfRatings;
    private List<Comment> comments;

    public Place() {
    }

    public Place(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public Long getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Long numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
