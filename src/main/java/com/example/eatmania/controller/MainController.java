package com.example.eatmania.controller;
import com.example.eatmania.Models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


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

    //creates a review for a foodItem
    @PostMapping("{userid}/fooditem/{id}/review")
    public ResponseEntity<ReviewModel> addReview2(@RequestBody ReviewModel aReview, @PathVariable("userid") long userid,@PathVariable ("id") long id){
        double sum=0;
        double average = 0;

        try {

            ReviewModel newReview = new ReviewModel(aReview.getRating(), aReview.getReview_content());
            newReview.setFood(foodRepo.findFoodModelByFoodId(id).get());
            newReview.setUser_id(userRepo.getReferenceById(userid));
            reviewRepo.save(newReview);

            List<ReviewModel> reviewsList = reviewRepo.getReviewModelByFood_FoodId(id);

                for (int i=0; i<reviewsList.size();i++) {
                    sum = sum + reviewsList.get(i).getRating();
                }
                average = sum / reviewsList.size();

                FoodModel foodItem = foodRepo.findFoodModelByFoodId(id).get();
                foodItem.setRating(Math.round(average * 10)/10.0);
                foodRepo.save(foodItem);

            return new ResponseEntity<>(newReview , HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Gets all the reviews for a foodItem
    @GetMapping("fooditem/{id}/reviews")
    public ResponseEntity<List<ReviewModel>> seeAllReviews(@PathVariable("id") long foodid){

        List<ReviewModel> reviewsList = new ArrayList<>();

        try{
            Optional<FoodModel> foodItem = foodRepo.findFoodModelByFoodId(foodid);
            if(foodItem.isPresent()){
                reviewsList = foodItem.get().getReviews();

            }
            return new ResponseEntity<>(reviewsList, HttpStatus.OK);
        }
        catch (Exception e){
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
        //search by reivew
    }

}
