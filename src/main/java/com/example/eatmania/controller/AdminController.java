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

        @Autowired
        FoodRepository foodRepo;


        @GetMapping("/GetAllAdmins")
        public List<AdminModel> GetAdmin(){

                List<AdminModel> admin = adminRepo.findAll();
                return admin;
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
        @GetMapping(path = "/restaurants")
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
        @PostMapping(path = "/restaurant/{id}/fooditem")
        public ResponseEntity<FoodModel> createFoodItem(@RequestBody FoodModel foodItem){
                try{
                        FoodModel newFoodItem = new FoodModel(foodItem.getFoodName(), foodItem.getFoodPrice(), foodItem.getDescription(), foodItem.getAdminID());
                        foodRepo.save(newFoodItem);
                        return new ResponseEntity<>(newFoodItem, HttpStatus.CREATED);
                }catch (Exception e){
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }


        //Deletes a food item
//        @DeleteMapping(path = "/restaurant/{id}/fooditem/{foodid}")
//        public ResponseEntity<HttpStatus> deleteFoodItem(@PathVariable("id") long restaurantId, @PathVariable("foodid") long foodId){
//
//
//                try{
//                        List<RestaurantModel> restaurants = new ArrayList<RestaurantModel>();
//                        restaurantRepo.findAll().forEach(restaurants::add);
//
//                        restaurants.get()
//                }
//
//
//                try{
//                        foodRepo.deleteById(id);
//                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//                }
//                catch(Exception e){
//                        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//        }


        //Retrives the foodlist
        @GetMapping(path = "/restaurant/{id}/foodlist")
        public ResponseEntity<List<FoodModel>> getFoodItems(@RequestParam(required = false) String searchString){

                try{
                        List<FoodModel> foodItems = new ArrayList<FoodModel>();

                        if(searchString == null){
                                foodRepo.findAll().forEach(foodItems::add);
                        }
                        else {
                                foodRepo.findFoodModelByFoodNameContainingIgnoreCase(searchString).forEach(foodItems::add);

                        }
                        if(foodItems.isEmpty()){
                                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                        }
                        return new ResponseEntity<>(foodItems, HttpStatus.OK);
                }
                catch (Exception e){
                        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }



}
