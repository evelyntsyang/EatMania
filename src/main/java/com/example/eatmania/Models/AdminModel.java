package com.example.eatmania.Models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "admin")  // Define the table name

public class AdminModel {

    //crrate column name, and getter setter, constructor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Define the column name for the ID
    private Long adminid;


    @Column(name = "first_name")  // Define the column name for foodName
    private String first_name;

    @Column(name = "last_name")  // Define the column name for foodPrice
    private String last_name;

    @Column(name = "email")  // Define the column name for foodPrice
    private String email;

    //constructor, getter and setter

    public AdminModel(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public AdminModel() {

    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
