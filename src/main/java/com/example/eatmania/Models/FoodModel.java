package com.example.eatmania.Models;

import java.util.List;

public class FoodModel {

    private String foodName;
    private int foodPrice;
    private String restaurantName;




    public FoodModel(String foodName,String restaurantName, int foodPrice) {
        this.foodName = foodName;
        this.restaurantName = restaurantName;
        this.foodPrice = foodPrice;
    }


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



}
