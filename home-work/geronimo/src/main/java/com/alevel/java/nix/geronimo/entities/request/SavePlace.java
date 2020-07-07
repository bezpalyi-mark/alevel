package com.alevel.java.nix.geronimo.entities.request;

public class SavePlace {

    private String name;

    private String categoryName;

    private String ratingName;

    private String cityName;

    private Boolean crossroads;

    public SavePlace() {
    }

    public SavePlace(String name, String categoryName, String ratingName, String cityName, Boolean crossroads) {
        this.name = name;
        this.categoryName = categoryName;
        this.ratingName = ratingName;
        this.cityName = cityName;
        this.crossroads = crossroads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    public Boolean isCrossroads() {
        return crossroads;
    }

//    public void setCrossroads(boolean crossroads) {
//        isCrossroads = crossroads;
//    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Boolean getCrossroads() {
        return crossroads;
    }

    public void setCrossroads(Boolean crossroads) {
        this.crossroads = crossroads;
    }
}
