package com.example.eatmania.Models;

import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE user_list AUTO_INCREMENT = 1;", nativeQuery = true)
    void resetId();
}


