package com.example.eatmania.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepo extends JpaRepository<FoodModel, Long> {

    List<FoodModel> findRestaurantModelByNameContainingIgnoreCase (String name);

}
