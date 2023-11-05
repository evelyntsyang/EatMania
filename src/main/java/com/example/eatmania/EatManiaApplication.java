package com.example.eatmania;

import com.example.eatmania.Models.FoodModel;
import com.example.eatmania.Models.FoodRepository;
import com.example.eatmania.Models.RestaurantRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EatManiaApplication {

	public static void main(String[] args) {

		SpringApplication.run(EatManiaApplication.class, args);

	}


	@Bean
	ApplicationRunner init (FoodRepository foodRepo, RestaurantRepository restRepo){

		return (arg) -> {

			foodRepo.deleteAll();
			foodRepo.resetId();

			List<FoodModel> foodModelList = new ArrayList<>();
			foodModelList.add(new FoodModel("Pizza", 10, "Pizza Palace"));
			foodModelList.add(new FoodModel("Burger", 5, "Burger Joint"));
			foodModelList.add(new FoodModel("Pasta", 12, "Italian Delight"));
			foodModelList.add(new FoodModel("Sushi", 15, "Sushi Haven"));
			foodModelList.add(new FoodModel("Taco", 7, "Taco Express"));
			foodModelList.add(new FoodModel("Salad", 8, "Healthy Bites"));
			foodModelList.add(new FoodModel("Steak", 20, "Steakhouse Grill"));
			foodModelList.add(new FoodModel("Ice Cream", 4, "Sweet Treats"));
			foodModelList.add(new FoodModel("Sandwich", 6, "Subway Eats"));
			foodModelList.add(new FoodModel("Fried Chicken", 9, "Crispy Delights"));

			foodRepo.saveAll(foodModelList);

		};
	}

}
