package com.example.eatmania.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "rating") // Define the column for the rating
    private Double rating = 0.0;


    @Column(name = "adminID")  // Define the column name for adminid
    private Long adminID;

    @Column(name = "image") //Define the column for the picture
    private String image;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RestaurantModel restaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<ReviewModel> reviews = new ArrayList<>();

    public FoodModel(String foodName, double foodPrice, String description, Long adminID, String image, Double rating) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.description = description;
        this.adminID = adminID;
        this.image = image;
        this.rating = rating;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setRestaurant(RestaurantModel restaurant) {
        this.restaurant = restaurant;
    }
    public long getAdminID() {
        return adminID;
    }

    public List<ReviewModel> getReviews() {
        return reviews;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
