package com.example.eatmania;

import com.example.eatmania.Models.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EatManiaApplication {


	public static void main(String[] args) {

		SpringApplication.run(EatManiaApplication.class, args);

	}


	@Bean
	ApplicationRunner init (FoodRepository foodRepo, RestaurantRepository restaurantRepo, AdminRepository adminRepository,UserRepository userRepo){

		return (arg) -> {

			foodRepo.deleteAll();
			foodRepo.resetId();

			List<FoodModel> foodModelList = new ArrayList<>();

			foodModelList.add(new FoodModel("Pizza", 10, "Western Palace","best italian pizza in town",100));
			foodModelList.add(new FoodModel("Burger", 5, "Western Palace","Savor the taste of a perfectly grilled gourmet burger from the renowned Burger Joint. The juicy patty, topped with fresh, crisp veggies and special sauce, is a masterpiece in every bite.",200));
			foodModelList.add(new FoodModel("Pasta", 12, "Western Palace","Dive into a world of culinary delight with the Italian Dream Pasta. Handcrafted by the skilled chefs at Italian Delight,this dish features al dente pasta, smothered in a luscious, secret-recipe tomato sauce, and garnished with Parmesan cheese.",300));
			foodModelList.add(new FoodModel("Sushi", 8, "Sushi Haven","Sushi Haven presents the Sushi Symphony where every roll is a work of art. Immerse yourself in a harmony of flavors as you enjoy the freshest fish and perfectly seasoned rice, all expertly rolled into delicate pieces of edible art.",200));
			foodModelList.add(new FoodModel("Taco", 7, "Taco Express","Taco Express serves up an explosion of flavor with its Tantalizing Tacos. Crispy shells cradle a medley of savory meats, fresh salsa, and zesty toppings, delivering a fiesta for your taste buds.",200));
			foodModelList.add(new FoodModel("Taco", 8, "Healthy Bites","Experience a healthier take on the classic taco with Healthy Crunch Taco.At Healthy Bites, you'll enjoy the perfect blend of fresh ingredients, whole-grain shells, and a dash of flavor that brings a nutritious twist to your favorite dish.",100));
			foodModelList.add(new FoodModel("Pasta", 20, "Burger & Pasta Fresh","Pasta Fresh presents Fresh Fusion Pasta, a culinary masterpiece that combines the flavors of the world. Savory meats, vibrant vegetables, and a symphony of sauces unite to create a culinary journey you won't soon forget.",200));
			foodModelList.add(new FoodModel("sushi", 4, "Sweet Sushi","Sweet Sushi offers a whimsical take on traditional sushi with the Sweet Sushi Delight. Dive into a world where sushi meets dessert, featuring delightful rice treats, candy fish, and sugary soy sauce. A unique and playful experience for your palate!",100));
			foodModelList.add(new FoodModel("Burger", 6, "Burger & Pasta Fresh","Burger Eats takes you on a journey to Burger Bliss. Sink your teeth into a sumptuous burger that's a taste explosion of high-quality ingredients and secret spices, all served with a side of culinary happiness.",200));
			foodModelList.add(new FoodModel("Taco", 9, "Crispy Delights","At Crispy Delights, the Crispy Delights Taco is a carnival for your taste buds. Expect a symphony of flavors and textures, from the crispy shell to the savory fillings and zesty sauces, all crafted to perfection.",300));

			foodRepo.saveAll(foodModelList);




			//insert restaurant data

			restaurantRepo.deleteAll();
			restaurantRepo.resetId();

			List<RestaurantModel> restaurantModelList = new ArrayList<>();
			restaurantModelList.add(new RestaurantModel("Western Palace","236678504","American",4.0,"Indulge in a taste of the Wild West at Western Palace. Our American cuisine is a journey through flavors inspired by the open range. Savor the richness of our dishes that bring the essence of the American frontier to your table.","www.westernpalace.com"));
			restaurantModelList.add(new RestaurantModel("Sushi Haven","7786241053","Asian",4.4,"Embark on a culinary adventure at Sushi Haven. Dive into the world of Asian cuisine, where traditional recipes meet modern innovation. Our sushi offerings are a haven of fresh and creative flavors that will tantalize your taste buds.","www.sushihaven.com"));
			restaurantModelList.add(new RestaurantModel("Taco Express","2364895217","Mexican",4.2,"Cuisine Type: Mexican" + "Get ready for a fiesta of flavors at Taco Express. We offer a journey through the vibrant tastes of Mexico with our delectable tacos. The express lane to Mexican cuisine starts here, with savory delights to satisfy your cravings.","www.tacoexpress.com"));
			restaurantModelList.add(new RestaurantModel("Burger & Pasta Fresh","2365551425","American",3.0,"At Burger & Pasta Fresh, we take classic American dishes to the next level. Experience the freshness and bold flavors of our burgers and pasta creations. Every bite is a celebration of culinary excellence.","www.burgerandpastafresh.com"));
			restaurantModelList.add(new RestaurantModel("Crispy Delights","2369045655","Mexican",3.9,"Crispy Delights invites you to savor the flavors of Mexico in every bite. Our menu features a symphony of crispy textures and zesty tastes. Each dish is a delight that will transport you to the heart of Mexican cuisine.","www.crispydelights.com"));
			restaurantModelList.add(new RestaurantModel("Healthy Bites","6044472581","Mexican",4.5,"Healthy Bites offers a delightful fusion of Italian and Mexican flavors. Our dishes are prepared with fresh ingredients, providing you with a nutritious twist on traditional favorites. Savor the perfect balance of tastes and textures.","www.healthybites.com"));
			restaurantModelList.add(new RestaurantModel("Sweet Sushi","6044421385","Asian",4.0,"Sweet Sushi presents a playful take on traditional Asian cuisine. Dive into a world where sushi meets dessert, featuring delightful rice treats, candy fish, and sugary soy sauce. It's a unique and whimsical culinary experience for your palate.","www.sweetsushi.com"));


			restaurantRepo.saveAll(restaurantModelList);



			//insert admin data

			adminRepository.deleteAll();
			adminRepository.resetId();


			List<AdminModel> AdminModelList = new ArrayList<>();
			AdminModelList.add(new AdminModel("Evelyn","Yang","yangt16@studentdouglascollege.ca"));

			adminRepository.saveAll(AdminModelList);


			//insert user data


		};
	}

}
