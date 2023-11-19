package com.example.eatmania.Models;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewModel, Long> {

    //List<ReviewModel>getAllByFood_FoodId(long id);

    List<ReviewModel>getReviewModelByFood_FoodId(long id);

    //List<ReviewModel>get

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE review_list AUTO_INCREMENT = 1;", nativeQuery = true)
    void resetId();
}
