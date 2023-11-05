package com.example.eatmania.controller;
import com.example.eatmania.Models.FoodModel;
import com.example.eatmania.Models.UserModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.VersionProps.print;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MainController {

    private List<String> userEmails ;
    private List<String> userPasswords;

    private List<UserModel> usersList;


    private List<FoodModel> foodList;

    private List<String> foodName;
    private List<String> restaurantName;
    private List<Integer> foodPrice;

    public MainController(){
        this.userEmails = new ArrayList<>();
        this.userPasswords = new ArrayList<>();
        this.usersList = new ArrayList<>();

        this.foodList=new ArrayList<>();
        this.foodName=new ArrayList<>();
        this.restaurantName=new ArrayList<>();
        this.foodPrice=new ArrayList<>();
    }


    public List<FoodModel> getFoodList() {
        return foodList;
    }
    public List<String> getFoodName() {
        return foodName;
    }

    public List<String> getRestaurantName() {
        return restaurantName;
    }
    public List<Integer> getFoodPrice() {
        return foodPrice;
    }


    public List<UserModel> getUsersList() {
        return usersList;
    }

    public List<String> getUserEmails() {
        return userEmails;
    }

    public List<String> getUserPasswords() {
        return userPasswords;
    }

    public void setUserEmails(List<String> userEmails) {
        this.userEmails = userEmails;
    }

    public void setUserPassword(List<String> userStrings) {
        this.userPasswords = userStrings;
    }
    public void setFoodList(List<FoodModel> foodList) {
        this.foodList = foodList;
    }

    public void setFoodName(List<String> foodName) {
        this.foodName = foodName;
    }

    public void setRestaurantName(List<String> foodSpicy) {
        this.restaurantName = foodSpicy;
    }

    public void setFoodPrice(List<Integer> foodPrice) {
        this.foodPrice = foodPrice;
    }
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        String name1 = "Eveyln";

        return name1;
    }

    @GetMapping("/login/{email}/{password}")
    public String LoginExample (@PathVariable String email, @PathVariable String password ){

       List<String> userEmail = this.getUserEmails();
       List<String> userPasswords = this.getUserPasswords();

       List<UserModel> userList = this.getUsersList();

       if( userEmail != null && password != null  && userList.size() != 0 ){

           for( int i = 0; i < userList.size() ; i++ ){

               if(userList.get(i).getUserEmail().equals(email)  && userList.get(i).getPassword().equals(password)){
                   return  "Your Login is succesfull";
               }else{
                   return "Your login is failed";
               }
           }
       }
        return "Login is not succesfull";
    }

    @PostMapping("/register/{email}/{password}")
    public String RegisterExample (@PathVariable String email, @PathVariable String password ){

        if( email != null  && password != null ){

            UserModel userModel = new UserModel(email, password);

            List<UserModel> usersList = this.getUsersList();

            if (usersList.isEmpty())  {
                this.usersList.add(userModel);
                return "Your account has been succesfull created with " + email + "and" + password;
            }

            for( int i = 0; i<usersList.size(); i++ ){

                 if( usersList.get(i).getUserEmail().equals(email) )  return  "Your account has already being created";
                 else this.usersList.add(userModel);
            }

            return "Your account has been succesfull created with " + email + "and" + password;

        }
        return "Email or password is missing  ";
    }

    //Create a model for food, and create an api to add food and get a list of foods, and a single food if user passes a food name
    //foodsearch example

    @PostMapping("/foodsearch/{foodname}")
    public String searchFood(@PathVariable String foodname) {

        List<FoodModel> foodList = this.getFoodList();
        List<String> foodName = this.getFoodName();

        FoodModel foundFood = null;

        if( foodname != null  ){


            boolean found = false;
            for(int i = 0; i < foodList.size() ; i++ ){

                if(foodList.get(i).getFoodName().equals(foodname) ){
                    foundFood = foodList.get(i);
                    found = true;
                    break;
                }

            }
            if (found) {
                // If a match is found, return the food item details
                return "Your food is available to view"+ foundFood.toString();
            } else {
                return "No result found for the food you search";
            }


        }


        return "foodname is missing";
    }




    //Create another api to find food in a given price range
}
