package com.example.eatmania.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "food_items")  // Define the table name
public class FoodModel {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")  // Define the column name for the ID
    private Long foodId;

    @Column(name = "food_name")  // Define the column name for foodName
    private String foodName;

    @Column(name = "food_price")  // Define the column name for foodPrice
    private double foodPrice;

    @Column(name = "description")  // Define the column name for description
    private String description;

    @Column(name = "adminID")  // Define the column name for adminid
    private Long adminID;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RestaurantModel restaurant;

    public FoodModel(String foodName, double foodPrice, String description, Long adminID) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.description = description;
        this.adminID = adminID;
    }

    public FoodModel() {
    }

    public Long getFoodId() {
        return foodId;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdminID(long adminID) {
        this.adminID = adminID;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }


    public String getFoodName() {
        return foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }


    public String getDescription() { return description;}

    public RestaurantModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantModel restaurant) {
        this.restaurant = restaurant;
    }

    public long getAdminID() {
        return adminID;
    }

}
