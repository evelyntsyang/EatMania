package com.example.eatmania.Models;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantModel, Long> {

   List<RestaurantModel> findRestaurantModelByNameContainingIgnoreCase (String name);
   List<RestaurantModel> findRestaurantModelByCuisineTypeIgnoreCase(String cuisineType);


    RestaurantModel save(RestaurantModel restaurant);

    void deleteAll();


    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE RESTAURANT AUTO_INCREMENT = 1;", nativeQuery = true)
    void resetId();


}
