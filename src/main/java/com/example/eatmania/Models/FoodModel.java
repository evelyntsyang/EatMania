package com.example.eatmania.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "food_items")  // Define the table name
public class FoodModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")  // Define the column name for the ID
    private Long id;

    @Column(name = "food_name")  // Define the column name for foodName
    private String foodName;

    @Column(name = "food_price")  // Define the column name for foodPrice
    private int foodPrice;

    @Column(name = "restaurant_name")  // Define the column name for restaurantName
    private String restaurantName;

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setRestaurantName(String foodSpicy) {
        this.restaurantName = foodSpicy;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public FoodModel(String foodName, int foodPrice, String restaurantName) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.restaurantName = restaurantName;
    }

    public FoodModel() {

    }
}
