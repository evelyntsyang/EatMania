package com.example.eatmania.Models;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodModel, Long> {
    //FoodModel save(FoodModel food);

    //List<FoodModel> findAll();

    FoodModel save(FoodModel food);

    void deleteAll();

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE food_items AUTO_INCREMENT = 1;", nativeQuery = true)
    void resetId();

}
