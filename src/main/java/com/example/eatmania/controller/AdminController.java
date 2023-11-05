package com.example.eatmania.controller;

import com.example.eatmania.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

        @Autowired
        RestaurantRepository restaurantRepo;

        @Autowired
        AdminRepository adminRepo;


        @GetMapping("/GetAllAdmins")
        public List<AdminModel> GetAdmin(){

                List<AdminModel> admin = adminRepo.findAll();
                return admin;
        }




        @GetMapping("/GetAllRestaurants")
        public List<RestaurantModel> GetRestaurant(){
                List<RestaurantModel> restaurants = restaurantRepo.findAll();
                return restaurants;
        }




        // Creates a new restaurant
        @PostMapping(path = "/restaurant")
        public ResponseEntity<RestaurantModel> createRestaurant(@RequestBody RestaurantModel restaurant){
                try{
                        RestaurantModel newRestaurant = new RestaurantModel(restaurant.getName(), restaurant.getPhoneNumber(), restaurant.getCuisineType(), restaurant.getRating(),
                                restaurant.getDescription(), restaurant.getWebsite());
                        restaurantRepo.save(newRestaurant);
                        return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
                }catch (Exception e){
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        //Deletes a restaurant
        @DeleteMapping(path = "/restaurant/{id}")
        public ResponseEntity<HttpStatus> deleteRestaurant(@PathVariable("id") long id){

                try{
                        restaurantRepo.deleteById(id);
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                catch(Exception e){
                        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        //Retrieves restaurant list with info by using search string (optional)
        @GetMapping(path = "/restaurantlist")
        public ResponseEntity<List<RestaurantModel>> getRestaurants(@RequestParam(required = false) String searchString){

                try{
                        List<RestaurantModel> restaurants = new ArrayList<RestaurantModel>();

                        if(searchString == null){
                                restaurantRepo.findAll().forEach(restaurants::add);
                        }
                        else {
                                restaurantRepo.findRestaurantModelByNameContainingIgnoreCase(searchString).forEach(restaurants::add);
                                restaurantRepo.findRestaurantModelByCuisineTypeIgnoreCase(searchString).forEach(restaurants::add);

                        }
                        if(restaurants.isEmpty()){
                                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                        }
                        return new ResponseEntity<>(restaurants, HttpStatus.OK);
                }
                catch (Exception e){
                        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }




        //Creates a food item

        //Deletes a food item





}
