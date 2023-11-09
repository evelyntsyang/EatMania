package com.example.eatmania.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "ReviewList")
public class ReviewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user_id;

    @Column(name = "food_id")
    private String food_id;

    @Column(name = "rating")
    private float  rating;

    @Column(name = "review_content")
    private String review_content;

    public ReviewModel(UserModel user_id, String food_id, float  rating, String review_content) {
        this.user_id = user_id;
        this.food_id = food_id;
        this.rating = rating;
        this.review_content = review_content;
    }

    public ReviewModel(int user_id, int food_id, float rating, String reviewcontent) {

    }

    public ReviewModel() {

    }


    public UserModel getUser_id() {
        return user_id;
    }

    public void setUser_id(UserModel user_id) {
        this.user_id = user_id;
    }

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }
}
