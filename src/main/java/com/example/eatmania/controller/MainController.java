package com.example.eatmania.controller;
import com.example.eatmania.Models.UserModel;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MainController {

    private List<String> userEmails ;
    private List<String> userPasswords;

    private List<UserModel> usersList;

    public MainController(){
        this.userEmails = new ArrayList<>();
        this.userPasswords = new ArrayList<>();
        this.usersList = new ArrayList<>();
    }

    public void setUserEmails(List<String> userEmails) {
        this.userEmails = userEmails;
    }

    public void setUserPassword(List<String> userStrings) {
        this.userPasswords = userStrings;
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
        return "Email or password is missing";
    }


    //Create a model for food, and create an api to add food and get a list of foods, and a single food if user passes a food name

    //Create another api to find food in a given price range
}
