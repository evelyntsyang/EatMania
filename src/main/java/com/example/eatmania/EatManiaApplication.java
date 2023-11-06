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
	ApplicationRunner init (FoodRepository foodRepo, RestaurantRepository restaurantRepo, AdminRepository adminRepository){

		return (arg) -> {

//			foodRepo.deleteAll();
//			foodRepo.resetId();
//
//			List<FoodModel> foodModelList = new ArrayList<>();


			//insert restaurant data with food options

			restaurantRepo.deleteAll();
			restaurantRepo.resetId();

			List<RestaurantModel> restaurantModelList = new ArrayList<>();

			RestaurantModel r1 = new RestaurantModel("Western Palace","236678504","American",4.0,"Indulge in a taste of the Wild West at Western Palace. Our American cuisine is a journey through flavors inspired by the open range. Savor the richness of our dishes that bring the essence of the American frontier to your table.","www.westernpalace.com");
			r1.addFoodItem(new FoodModel("Pizza", 10,"best italian pizza in town",1L));

			RestaurantModel r2 = new RestaurantModel("Sushi Haven","7786241053","Asian",4.4,"Embark on a culinary adventure at Sushi Haven. Dive into the world of Asian cuisine, where traditional recipes meet modern innovation. Our sushi offerings are a haven of fresh and creative flavors that will tantalize your taste buds.","www.sushihaven.com");
			r2.addFoodItem(new FoodModel("Sushi", 8,"Sushi Haven presents the Sushi Symphony where every roll is a work of art. Immerse yourself in a harmony of flavors as you enjoy the freshest fish and perfectly seasoned rice, all expertly rolled into delicate pieces of edible art.",1L));

			RestaurantModel r3 = new RestaurantModel("Taco Express","2364895217","Mexican",4.2,"Cuisine Type: Mexican" + "Get ready for a fiesta of flavors at Taco Express. We offer a journey through the vibrant tastes of Mexico with our delectable tacos. The express lane to Mexican cuisine starts here, with savory delights to satisfy your cravings.","www.tacoexpress.com");
			r3.addFoodItem(new FoodModel("Taco", 7, "Taco Express serves up an explosion of flavor with its Tantalizing Tacos. Crispy shells cradle a medley of savory meats, fresh salsa, and zesty toppings, delivering a fiesta for your taste buds.",2L));

			RestaurantModel r4 = new RestaurantModel("Burger & Pasta Fresh","2365551425","American",3.0,"At Burger & Pasta Fresh, we take classic American dishes to the next level. Experience the freshness and bold flavors of our burgers and pasta creations. Every bite is a celebration of culinary excellence.","www.burgerandpastafresh.com");
			r4.addFoodItem(new FoodModel("Burger", 5.0, "Savor the taste of a perfectly grilled gourmet burger from the renowned Burger Joint. The juicy patty, topped with fresh, crisp veggies and special sauce, is a masterpiece in every bite.",2L));
			r4.addFoodItem(new FoodModel("Pasta", 12,"Dive into a world of culinary delight with the Italian Dream Pasta. Handcrafted by the skilled chefs at Italian Delight,this dish features al dente pasta, smothered in a luscious, secret-recipe tomato sauce, and garnished with Parmesan cheese.",3L));
			r4.addFoodItem(new FoodModel("Pasta", 20, "Pasta Fresh presents Fresh Fusion Pasta, a culinary masterpiece that combines the flavors of the world. Savory meats, vibrant vegetables, and a symphony of sauces unite to create a culinary journey you won't soon forget.",2L));


			RestaurantModel r5 = new RestaurantModel("Crispy Delights","2369045655","Mexican",3.9,"Crispy Delights invites you to savor the flavors of Mexico in every bite. Our menu features a symphony of crispy textures and zesty tastes. Each dish is a delight that will transport you to the heart of Mexican cuisine.","www.crispydelights.com");
			r5.addFoodItem(new FoodModel("Taco", 8, "Experience a healthier take on the classic taco with Healthy Crunch Taco.At Healthy Bites, you'll enjoy the perfect blend of fresh ingredients, whole-grain shells, and a dash of flavor that brings a nutritious twist to your favorite dish.",3L));


			RestaurantModel r6 = new RestaurantModel("Healthy Bites","6044472581","Mexican",4.5,"Healthy Bites offers a delightful fusion of Italian and Mexican flavors. Our dishes are prepared with fresh ingredients, providing you with a nutritious twist on traditional favorites. Savor the perfect balance of tastes and textures.","www.healthybites.com");
			r6.addFoodItem(new FoodModel("Burger", 6, "Burger Eats takes you on a journey to Burger Bliss. Sink your teeth into a sumptuous burger that's a taste explosion of high-quality ingredients and secret spices, all served with a side of culinary happiness.",2L));
			r6.addFoodItem(new FoodModel("Taco", 9, "At Crispy Delights, the Crispy Delights Taco is a carnival for your taste buds. Expect a symphony of flavors and textures, from the crispy shell to the savory fillings and zesty sauces, all crafted to perfection.",3L));

			RestaurantModel r7 = new RestaurantModel("Sweet Sushi","6044421385","Asian",4.0,"Sweet Sushi presents a playful take on traditional Asian cuisine. Dive into a world where sushi meets dessert, featuring delightful rice treats, candy fish, and sugary soy sauce. It's a unique and whimsical culinary experience for your palate.","www.sweetsushi.com");
			r7.addFoodItem(new FoodModel("sushi", 4, "Sweet Sushi offers a whimsical take on traditional sushi with the Sweet Sushi Delight. Dive into a world where sushi meets dessert, featuring delightful rice treats, candy fish, and sugary soy sauce. A unique and playful experience for your palate!",1L));



			restaurantModelList.add(r1);
			restaurantModelList.add(r2);
			restaurantModelList.add(r3);
			restaurantModelList.add(r4);
			restaurantModelList.add(r5);
			restaurantModelList.add(r6);
			restaurantModelList.add(r7);

			restaurantRepo.saveAll(restaurantModelList);




			//insert admin data

			adminRepository.deleteAll();
			adminRepository.resetId();


			List<AdminModel> AdminModelList = new ArrayList<>();
			AdminModelList.add(new AdminModel("Evelyn","Yang","yangt16@studentdouglascollege.ca"));

			adminRepository.saveAll(AdminModelList);
		};
	}

}
