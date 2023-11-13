package com.example.eatmania.Models;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<ReviewModel, Long> {

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE review_list AUTO_INCREMENT = 1;", nativeQuery = true)
    void resetId();
}
