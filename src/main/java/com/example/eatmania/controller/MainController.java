package com.example.eatmania.controller;
import com.example.eatmania.Models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
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
    public ResponseEntity<UserModel> LoginExample (@PathVariable String email, @PathVariable String password ){


       if( email != null && password != null ){

           List<UserModel> userList = userRepo.findAll();

           for(int i = 0 ; i < userList.size(); i++){
               if( userList.get(i).getUserEmail().equals(email)  &&  userList.get(i).getPassword().equals(password)){
                   return ResponseEntity.ok( userList.get(i) );
               }
           }

           return ResponseEntity.badRequest().build(); // Use build() to create a ResponseEntity
       }

        // Change this part later.
        return ResponseEntity.badRequest().build(); // Use build() to create a ResponseEntity
    }


 @Autowired
    UserRepository userRepo;

    @PostMapping("/register")
    public ResponseEntity<String> registerExample(@RequestBody UserModel userModel) {
        if (userModel != null && userModel.getUserEmail() != null && userModel.getPassword() != null) {
            String email = userModel.getUserEmail();

            List<UserModel> userList = userRepo.findAll();

            for (UserModel user : userList) {
                if (user.getUserEmail().equals(email)) {
                    return ResponseEntity.badRequest().body("The account has already existed");
                }
            }

            userRepo.save(userModel);
            return ResponseEntity.ok("Your account has been successfully created with " + email + " and " + userModel.getPassword());
        }
        return ResponseEntity.badRequest().body("Email or password is missing");
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


        //not used

        // userRepo.saveAll(usersList1); // Save the list of UserModel instances to the database

        return ResponseEntity.status(HttpStatus.CREATED).body("User(s) registered successfully");
    }



    //not working
    @PostMapping("/foodsearch/{foodname}")
    public ResponseEntity<?> searchFood(@PathVariable String foodname) {


        try {
            if (foodname != null) {
                List<FoodModel> matchingFoods = new ArrayList<>();
                List<FoodModel> foodList = foodRepo.findAll();

                for (FoodModel food : foodList) {
                    if (food.getFoodName().toLowerCase().contains(foodname.toLowerCase())) {
                        matchingFoods.add(food);
                    }
                }

                if (!matchingFoods.isEmpty()) {
                    return ResponseEntity.ok(matchingFoods);
                } else {
                    return ResponseEntity.notFound().build();
                }
            }

            return ResponseEntity.badRequest().body("Please enter a valid food name");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred.");
        }
    }




    //not working
    @PostMapping("/foodsearch1/{foodprice}")
    public ResponseEntity<?> searchPrice(@PathVariable double foodprice){


        if( foodprice >0  ){
            List<FoodModel> matchingFoods = new ArrayList<>();
            List<FoodModel> foodList = foodRepo.findAll();

            for(FoodModel food : foodList){
                if (Double.compare(food.getFoodPrice(), foodprice) == 0) {
                    matchingFoods.add(food);
                }
            }

            if (!matchingFoods.isEmpty()) {
                return ResponseEntity.ok(matchingFoods); // Return a list of matching food items
            } else {
                return ResponseEntity.notFound().build(); // Return 404 Not Found if no matches are found
            }

        }

        return ResponseEntity.badRequest().body("Please enter a food price ");
    }



    @GetMapping("/GetAllFoods")
    public List<FoodModel> GetFood(){
        //foodRepo - database name
        List<FoodModel> foods = foodRepo.findAll();
        return foods;
    }



    //http://localhost:8080/api/searchFoodByName?foodName=sushi
    @GetMapping("/searchFoodByName")
    public List<FoodModel> searchFoodByName(@RequestParam String foodName) {
        // Use the repository method to search for food by name
        return foodRepo.findFoodModelByFoodNameContainingIgnoreCase(foodName);
    }


    //search by price
    //http://localhost:8080/api/searchByPrice?maxPrice=10
    @GetMapping("/searchByPrice")
    public ResponseEntity<?> searchByPrice(@RequestParam double maxPrice) {

        if (maxPrice >= 0) {
            List<FoodModel> matchingFoods = new ArrayList<>();
            List<FoodModel> foodList = foodRepo.findAll();

            for (FoodModel food : foodList) {
                if (food.getFoodPrice() <= maxPrice) {
                    matchingFoods.add(food);
                }
            }

            if (!matchingFoods.isEmpty()) {
                return ResponseEntity.ok(matchingFoods);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No food items found within the specified price range.");
            }
        }

        return ResponseEntity.badRequest().body("Please enter a valid food price");

    }



    //search by reivew
    @GetMapping("/GetAllReviews")
    public List<ReviewModel> GetReview(){
        //foodRepo - database name
        List<ReviewModel> reviews = reviewRepo.findAll();
        return reviews;
    }


    //searchbyreviewratingandfooditem
    //http://localhost:8080/api/searchByPrice?maxPrice=10
    @GetMapping("/searchByReview")
    public ResponseEntity<?> searchByReview(@RequestParam double maxReview, @RequestParam Long foodId) {

        if (maxReview >= 0) {

            List<ReviewModel> review_List = reviewRepo.findAll();

            if (!review_List.isEmpty()) {
                // Sort reviews by rating in descending order
                review_List.sort(Comparator.comparingDouble(ReviewModel::getRating).reversed());

                // Filter reviews based on the maximum rating
                List<ReviewModel> matchingReviews = review_List.stream()
                        .filter(review -> review.getRating() <= maxReview)
                        .collect(Collectors.toList());

                if (!matchingReviews.isEmpty()) {
                    return ResponseEntity.ok(matchingReviews);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("No review found within the specified rating range for the given food ID.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No reviews available for the given food ID.");
            }
        }
        return ResponseEntity.badRequest().body("Please enter a valid review rating.");
    }
}


