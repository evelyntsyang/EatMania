package com.example.eatmania.controller;

import com.example.eatmania.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
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
                }
                catch (Exception e){

                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }


        //Get a restaurant by ID
        @GetMapping(path = "/restaurants/{id}")
        public ResponseEntity<RestaurantModel> getRestaurantById(@PathVariable("id") long id){
                try{
                        Optional<RestaurantModel> restaurant = restaurantRepo.findRestaurantModelByRestaurantId(id);

                        if(restaurant.isPresent()) {
                                return new ResponseEntity<>(restaurant.get(), HttpStatus.OK);
                        } else {
                                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                        }
                }
                catch (Exception e){
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

        //Get the foodlist of a restaurant by ID
        @GetMapping(path = "/restaurants/{id}/foodlist")
        public ResponseEntity<List<FoodModel>> getRestaurantFoodItems(@PathVariable("id") long id){

                List<FoodModel> foodItems;

                try{
                        Optional<RestaurantModel> restaurant = restaurantRepo.findRestaurantModelByRestaurantId(id);

                        if(restaurant.isPresent()) {
                                foodItems = restaurant.get().getFoodItems();
                                return new ResponseEntity<>(foodItems, HttpStatus.OK);
                        } else {
                                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                        }
                }
                catch (Exception e){
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }


        //Deletes a restaurant by ID
        @DeleteMapping(path = "/restaurants/{id}")
        public ResponseEntity<HttpStatus> deleteRestaurant(@PathVariable("id") long id){

                try{
                        restaurantRepo.deleteById(id);
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                catch(Exception e){
                        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }




        //Creates a food item and adds the restaurant that it belongs to.
        @PostMapping(path = "/restaurants/{id}/fooditem")
        public ResponseEntity<FoodModel> createFoodItem(@RequestBody FoodModel foodItem, @PathVariable ("id") long id){

                try {
                        FoodModel newFood = new FoodModel(foodItem.getFoodName(), foodItem.getFoodPrice(), foodItem.getDescription(), foodItem.getAdminID());
                        newFood.setRestaurant(restaurantRepo.findRestaurantModelByRestaurantId(id).get());
                        foodRepo.save(newFood);
                        return new ResponseEntity<>(newFood , HttpStatus.CREATED);
                } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }


        //Deletes a food item
        @DeleteMapping(path = "/restaurants/{id}/fooditem/{foodid}")
        public ResponseEntity<HttpStatus> deleteFoodItem(@PathVariable("id") long restaurantId, @PathVariable("foodid") long foodId){


                try{
                        foodRepo.deleteById(foodId);
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                catch(Exception e){
                        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }




}
