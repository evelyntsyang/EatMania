package com.example.eatmania.Models;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface AdminRepository  extends JpaRepository<AdminModel, Long> {


    AdminModel save(AdminModel admin);

    void deleteAll();

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE admin AUTO_INCREMENT = 1;", nativeQuery = true)
    void resetId();
}
