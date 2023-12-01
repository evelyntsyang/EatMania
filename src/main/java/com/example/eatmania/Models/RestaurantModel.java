package com.example.eatmania.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class RestaurantModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private long restaurantId;

    @Column(name = "Name")
    private String name;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "CuisineType")
    private String cuisineType;

    @Column(name = "Rating")
    private double rating;

    @Column(name = "Description")
    private String description;

    @Column(name = "Website")
    private String website;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<FoodModel> foodItems = new ArrayList<>();

    public List<FoodModel> getFoodItems(){
        return foodItems;
    }

    public void addFoodItem(FoodModel foodItem){
        this.foodItems.add(foodItem);
    }

    public void removeFoodItem(FoodModel foodItem){
        this.foodItems.remove(foodItem);
    }

    public RestaurantModel(){};

    public RestaurantModel(String name, String phoneNumber, String cuisineType, double rating, String description, String website) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.cuisineType = cuisineType;
        this.rating = rating;
        this.description = description;
        this.website = website;
    }


    public long getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setFoodItems(List<FoodModel> foodItems) {
        this.foodItems = foodItems;
    }
}
