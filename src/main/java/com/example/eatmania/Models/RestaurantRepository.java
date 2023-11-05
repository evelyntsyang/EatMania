package com.example.eatmania.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantModel, Long> {

    List<RestaurantModel> findRestaurantModelByNameContainingIgnoreCase (String name);
    List<RestaurantModel> findRestaurantModelByCuisineTypeIgnoreCase(String cuisineType);



}
