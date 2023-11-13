package com.example.eatmania.controller;
import com.example.eatmania.Models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class MainController {



    @Autowired
    FoodRepository foodRepo;


    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        String name1 = "Eveyln";

        return name1;
    }

    @GetMapping("/login/{email}/{password}")
    public String LoginExample (@PathVariable String email, @PathVariable String password ){


       if( email != null && password != null ){

           List<UserModel> userList = userRepo.findAll();

           for(int i = 0 ; i < userList.size(); i++){
               if( userList.get(i).getUserEmail().equals(email)  &&  userList.get(i).getPassword().equals(password)){
                   return "You successfully login";
               }
           }

           return "Your email or password is incorrect";
       }
       //Change this part later.
        return "Your email or password is missingS";
    }



 @Autowired
    UserRepository userRepo;

    @PostMapping("/register/{email}/{password}/{firstname}/{lastname}/{address}/{phonenumber}")
    public String RegisterExample (@PathVariable String email, @PathVariable String password,@PathVariable String firstname,
                                   @PathVariable String lastname, @PathVariable String address, @PathVariable String phonenumber){


        if( email != null  && password != null ){

            UserModel userModel1 = new UserModel(email, password,firstname,lastname,address,phonenumber);

            List<UserModel> userList = userRepo.findAll();

            for(int i = 0 ; i < userList.size(); i++){
                if( userList.get(i).getUserEmail().equals(email)){
                    return "The account has already existed";
                }
            }

            userRepo.save(userModel1);
            return "Your account has been successful created with " + email + "and" + password;

        }
        return "Email or password is missing  ";
    }


    @Autowired
    ReviewRepository reviewRepo;
    @PostMapping("/review")
    public ResponseEntity<String> addReview(@RequestBody ReviewModel newReview){

        ReviewModel savedReview = reviewRepo.save(newReview);
        return ResponseEntity.ok("Your review has been submitted. Thank you for your contribution.");


    }


    @PostMapping("{userid}/fooditem/{id}/review")
    public ResponseEntity<ReviewModel> addReview2(@RequestBody ReviewModel aReview, @PathVariable("userid") long userid,@PathVariable ("id") long id){
        try {
            ReviewModel newReview = new ReviewModel(aReview.getRating(), aReview.getReview_content());
            newReview.setFood(foodRepo.findFoodModelByFoodId(id).get());
            newReview.setUser_id(userRepo.getReferenceById(userid));
            reviewRepo.save(newReview);
            return new ResponseEntity<>(newReview , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }





    @PostMapping("/register1")
    public ResponseEntity<String> register(@RequestBody List<UserModel> userList1) {


        // You can perform validation and other business logic here if needed

        // userRepo.saveAll(usersList1); // Save the list of UserModel instances to the database

        return ResponseEntity.status(HttpStatus.CREATED).body("User(s) registered successfully");
    }



    @PostMapping("/foodsearch/{foodname}")
    public ResponseEntity<?> searchFood(@PathVariable String foodname) {


        if( foodname != null  ){
            List<FoodModel> matchingFoods = new ArrayList<>();
            List<FoodModel> foodList = foodRepo.findAll();

            for(FoodModel food : foodList){
                if (food.getFoodName().toLowerCase().contains(foodname.toLowerCase())) {
                    matchingFoods.add(food); // Collect all matching food items
                }
            }

            if (!matchingFoods.isEmpty()) {
                return ResponseEntity.ok(matchingFoods); // Return a list of matching food items
            } else {
                return ResponseEntity.notFound().build(); // Return 404 Not Found if no matches are found
            }



        }

        return ResponseEntity.badRequest().body("Please enter a food name");
    }




    @GetMapping("/GetAllFoods")
    public List<FoodModel> GetFood(){
        //foodRepo - database name
        List<FoodModel> foods = foodRepo.findAll();
        return foods;
    }



    @GetMapping("/searchFoodByName")
    public List<FoodModel> searchFoodByName(@RequestParam String foodName) {
        // Use the repository method to search for food by name
        return foodRepo.findFoodModelByFoodNameContainingIgnoreCase(foodName);
    }


}
