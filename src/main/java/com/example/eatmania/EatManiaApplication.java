package com.example.eatmania;

import com.example.eatmania.Models.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class EatManiaApplication {


	public static void main(String[] args) {

		SpringApplication.run(EatManiaApplication.class, args);

	}


	@Bean
	ApplicationRunner init (FoodRepository foodRepo, RestaurantRepository restaurantRepo, AdminRepository adminRepository,UserRepository userRepo, ReviewRepository reviewRepo){

		return (arg) -> {


//			reset the database everytime
			restaurantRepo.deleteAll();
			restaurantRepo.resetId();
			List<RestaurantModel> restaurantModelList = new ArrayList<>();


			RestaurantModel r1 = new RestaurantModel("Western Palace","236678504","American",4.0,"Indulge in a taste of the Wild West at Western Palace. Our American cuisine is a journey through flavors inspired by the open range. Savor the richness of our dishes that bring the essence of the American frontier to your table.","www.westernpalace.com");
			RestaurantModel r2 = new RestaurantModel("Sushi Haven","7786241053","Asian",4.4,"Embark on a culinary adventure at Sushi Haven. Dive into the world of Asian cuisine, where traditional recipes meet modern innovation. Our sushi offerings are a haven of fresh and creative flavors that will tantalize your taste buds.","www.sushihaven.com");
			RestaurantModel r3 = new RestaurantModel("Taco Express","2364895217","Mexican",4.2,"Cuisine Type: Mexican" + "Get ready for a fiesta of flavors at Taco Express. We offer a journey through the vibrant tastes of Mexico with our delectable tacos. The express lane to Mexican cuisine starts here, with savory delights to satisfy your cravings.","www.tacoexpress.com");
			RestaurantModel r4 = new RestaurantModel("Burger & Pasta Fresh","2365551425","American",3.0,"At Burger & Pasta Fresh, we take classic American dishes to the next level. Experience the freshness and bold flavors of our burgers and pasta creations. Every bite is a celebration of culinary excellence.","www.burgerandpastafresh.com");
			RestaurantModel r5 = new RestaurantModel("Crispy Delights","2369045655","Mexican",3.9,"Crispy Delights invites you to savor the flavors of Mexico in every bite. Our menu features a symphony of crispy textures and zesty tastes. Each dish is a delight that will transport you to the heart of Mexican cuisine.","www.crispydelights.com");
			RestaurantModel r6 = new RestaurantModel("Healthy Bites","6044472581","Mexican",4.5,"Healthy Bites offers a delightful fusion of Italian and Mexican flavors. Our dishes are prepared with fresh ingredients, providing you with a nutritious twist on traditional favorites. Savor the perfect balance of tastes and textures.","www.healthybites.com");
			RestaurantModel r7 = new RestaurantModel("Sweet Sushi","6044421385","Asian",4.0,"Sweet Sushi presents a playful take on traditional Asian cuisine. Dive into a world where sushi meets dessert, featuring delightful rice treats, candy fish, and sugary soy sauce. It's a unique and whimsical culinary experience for your palate.","www.sweetsushi.com");
			RestaurantModel r8 = new RestaurantModel("Smokehouse Grill", "555-789-1234", "American", 4.5, "Experience the essence of American barbecue at Smokehouse Grill. Our menu boasts a selection of slow-smoked meats, from tender pulled pork to flavorful brisket. Accompanied by classic sides like coleslaw and baked beans. ", "www.smokehousegrill.com");
			RestaurantModel r9 = new RestaurantModel("City Diner", "555-987-6543", "American", 4.2, "City Diner offers a nostalgic journey through classic American diner fare. From hearty breakfasts to juicy burgers and hand-spun milkshakes, our menu brings the timeless flavors of comfort food to the heart of the city.", "www.citydiner.com");
			RestaurantModel r10 = new RestaurantModel("Sakura Palace", "555-123-4567", "Asian", 4.3, "Embark on a culinary journey at Sakura Palace, where traditional Asian flavors meet contemporary elegance. Our menu features a fusion of Japanese, Chinese, and Thai cuisines, offering a diverse selection of sushi, dim sum, and flavorful stir-fries. ", "www.sakurapalace.com");

			restaurantModelList.add(r1);
			restaurantModelList.add(r2);
			restaurantModelList.add(r3);
			restaurantModelList.add(r4);
			restaurantModelList.add(r5);
			restaurantModelList.add(r6);
			restaurantModelList.add(r7);
			restaurantModelList.add(r8);
			restaurantModelList.add(r9);
			restaurantModelList.add(r10);

			restaurantRepo.saveAll(restaurantModelList);

//			reset the database everytime
			foodRepo.deleteAll();
			foodRepo.resetId();

			List<FoodModel> foodModelList = new ArrayList<>();

//			insert food options and assign restaurants

			FoodModel f1 = new FoodModel("Beef Pizza", 10,"best italian pizza in town",1L, "https://bit.ly/3MRw5Zj", 3.0);
			f1.setRestaurant(r1);
			foodModelList.add(f1);

			FoodModel f11 = new FoodModel("Veggie Pizza", 14,"Fresh garden veggies atop a crispy crust and melty mozzarella, a flavorful choice for vegetarians.\n",1L, "http://surl.li/npjmx");
			f11.setRestaurant(r1);
			foodModelList.add(f11);

			FoodModel f12 = new FoodModel("Chicken Pizza", 12,"Juicy seasoned chicken on a zesty tomato base, topped with cheese—satisfying and flavorful for chicken enthusiasts.",1L, "http://surl.li/npksg");
			f12.setRestaurant(r1);
			foodModelList.add(f12);

			FoodModel f2 = new FoodModel("Burger", 5.0, "Savor the taste of a perfectly grilled gourmet burger from the renowned Burger Joint. The juicy patty, topped with fresh, crisp veggies and special sauce, is a masterpiece in every bite.", 1L, "https://bit.ly/3G7yxqx", 4.0);
			f2.setRestaurant(r4);
			foodModelList.add(f2);


			FoodModel f3 = new FoodModel("Pasta", 12,"Dive into a world of culinary delight with the Italian Dream Pasta. Handcrafted by the skilled chefs at Italian Delight,this dish features al dente pasta, smothered in a luscious, secret-recipe tomato sauce, and garnished with Parmesan cheese.",1L, "https://bit.ly/47mfbKf", 3.7);
			f3.setRestaurant(r4);
			foodModelList.add(f3);

			FoodModel f4 = new FoodModel("California Sushi", 8,"Sushi Haven presents the Sushi Symphony where every roll is a work of art. Immerse yourself in a harmony of flavors as you enjoy the freshest fish and perfectly seasoned rice, all expertly rolled into delicate pieces of edible art.",1L, "https://bit.ly/49I0dzQ", 3.9);
			f4.setRestaurant(r2);
			foodModelList.add(f4);

			FoodModel f5 = new FoodModel("Fish Taco", 7, "Taco Express serves up an explosion of flavor with its Tantalizing Tacos. Crispy shells cradle a medley of savory meats, fresh salsa, and zesty toppings, delivering a fiesta for your taste buds.",1L, "http://surl.li/npkzi", 4.3);
			f5.setRestaurant(r3);
			foodModelList.add(f5);

			FoodModel f6 = new FoodModel("Fish Taco ", 8, "Experience a healthier take on the classic taco with Healthy Crunch Taco.At Healthy Bites, you'll enjoy the perfect blend of fresh ingredients, whole-grain shells, and a dash of flavor that brings a nutritious twist to your favorite dish.",1L, "https://bit.ly/3QMaxOT", 4.9);
			f6.setRestaurant(r5);
			foodModelList.add(f6);

			FoodModel f7 = new FoodModel("Pasta", 20, "Pasta Fresh presents Fresh Fusion Pasta, a culinary masterpiece that combines the flavors of the world. Savory meats, vibrant vegetables, and a symphony of sauces unite to create a culinary journey you won't soon forget.",1L, "https://bit.ly/3sIecVQ", 4.8);
			f7.setRestaurant(r1);
			foodModelList.add(f7);

			FoodModel f8 = new FoodModel("Burger", 6, "Burger Eats takes you on a journey to Burger Bliss. Sink your teeth into a sumptuous burger that's a taste explosion of high-quality ingredients and secret spices, all served with a side of culinary happiness.",1L, "https://bit.ly/3R7zt4P", 2.2);
			f8.setRestaurant(r6);
			foodModelList.add(f8);

			FoodModel f9 = new FoodModel("Taco", 9, "At Crispy Delights, the Crispy Delights Taco is a carnival for your taste buds. Expect a symphony of flavors and textures, from the crispy shell to the savory fillings and zesty sauces, all crafted to perfection.",1L, "https://bit.ly/47ijgiB", 4.3);
			f9.setRestaurant(r6);
			foodModelList.add(f9);

			FoodModel f10 = new FoodModel("California Sushi", 4, "Sweet Sushi offers a whimsical take on traditional sushi with the Sweet Sushi Delight. Dive into a world where sushi meets dessert, featuring delightful rice treats, candy fish, and sugary soy sauce. A unique and playful experience for your palate!",1L, "http://surl.li/npjxs",3.5);
			f10.setRestaurant(r7);
			foodModelList.add(f10);

			FoodModel f13 = new FoodModel("Beef Pizza", 12, "Indulge in our Beef Supreme Pizza, a carnivore's delight! Loaded with seasoned beef, savory sauce, melted cheese, and your favorite toppings, every slice is a flavorful journey.", 1L, "http://surl.li/npktf");
			f13.setRestaurant(r8);
			foodModelList.add(f13);

			FoodModel f14 = new FoodModel("Beef Pizza", 14, "Enjoy the classic flavor of our Beef Pizza at City Diner. Succulent seasoned beef, tangy tomato sauce, and gooey melted cheese come together on a perfect crust. ", 1L, "http://surl.li/npkun");
			f14.setRestaurant(r9);
			foodModelList.add(f14);

			FoodModel f15 = new FoodModel("Chicken Pizza", 13, "Savor the flavor of our Grilled Chicken Pizza at City Diner. Tender grilled chicken, a medley of fresh vegetables, and a blend of cheeses make every bite a delightful experience.", 1L, "http://surl.li/npksg");
			f15.setRestaurant(r9);
			foodModelList.add(f15);

			FoodModel f16 = new FoodModel("Veggie Pizza", 9, "Delight in the freshness of our Garden Veggie Pizza at Smokehouse Grill. A medley of colorful vegetables, including bell peppers, tomatoes, onions, and olives.", 1L, "http://surl.li/npkrk");
			f16.setRestaurant(r8);
			foodModelList.add(f16);

			FoodModel f17 = new FoodModel("Burger", 9, "A juicy beef patty, fresh lettuce, ripe tomatoes, and a special sauce, all sandwiched between a soft bun. It's a timeless American favorite that promises a satisfying bite.", 1L, "http://surl.li/npkqw");
			f17.setRestaurant(r8);
			foodModelList.add(f17);

			FoodModel f18 = new FoodModel("Pasta", 12, "Experience the fusion of Asian flavors with our Asian Fusion Pasta at Sakura Palace. Thin noodles tossed in a savory blend of soy sauce, ginger, and garlic, accompanied by fresh vegetables and your choice of protein.", 1L, "http://surl.li/npjno");
			f18.setRestaurant(r10);
			foodModelList.add(f18);

			FoodModel f19 = new FoodModel("California Sushi", 15, "Indulge in the freshness of our California Roll at Ocean Breeze Sushi. A delightful combination of crab, avocado, and cucumber wrapped in seasoned rice and seaweed. Served with soy sauce and pickled ginger.", 1L, "http://surl.li/npkvd");
			f19.setRestaurant(r4);
			foodModelList.add(f19);

			FoodModel f20 = new FoodModel("California Sushi", 8, "Embark on a culinary journey with our California Dream Roll at Sakura Palace. Succulent crab, creamy avocado, and crunchy cucumber come together in a perfect harmony of flavors.", 1L, "http://surl.li/npjxs");
			f20.setRestaurant(r10);
			foodModelList.add(f20);

			FoodModel f21 = new FoodModel("Miso Ramen", 16, "Savor the authentic taste of our Miso Ramen Bowl at Ramen Haven. Rich, savory broth, tender noodles, and a medley of fresh vegetables create a comforting and flavorful experience.", 1L, "http://surl.li/npjop");
			f21.setRestaurant(r2);
			foodModelList.add(f21);

			FoodModel f22 = new FoodModel("Pork Taco", 5, "Topped with fresh salsa, cilantro, and a hint of lime, each bite is a fiesta of taste sensations.", 1L, "http://surl.li/npkor");
			f22.setRestaurant(r3);
			foodModelList.add(f22);

			FoodModel f23 = new FoodModel("Pork Taco", 10, "Experience the savory delight of our Grilled Pork Taco at Taqueria Fiesta. Juicy pork, expertly grilled and seasoned, is folded into a soft corn tortilla.", 1L, "http://surl.li/nplan");
			f23.setRestaurant(r6); // Assuming r6 is the instance of the restaurant with ID 6
			foodModelList.add(f23);

			FoodModel f24 = new FoodModel("Sashimi Bowl", 20, "Indulge in the freshness of our Assorted Sashimi Platter at Sweet Sushi. Immerse yourself in a culinary journey with a selection of premium sashimi, including succulent slices of tuna, salmon, and yellowtail.", 1L, "http://surl.li/npkxv");
			f24.setRestaurant(r7); // Assuming r7 is the instance of the restaurant with ID 7
			foodModelList.add(f24);

			FoodModel f25 = new FoodModel("Sashimi Bowl", 22, "Experience the artistry of our Chef's Sashimi Selection at Sushi Bliss. Delight in an exquisite array of fresh sashimi, carefully selected and expertly sliced. From buttery tuna to delicate yellowtail.", 1L, "http://surl.li/npjqp");
			f25.setRestaurant(r2); // Assuming r2 is the instance of the restaurant with ID 2
			foodModelList.add(f25);

			FoodModel f26 = new FoodModel("Beef Taco", 11, "Satisfy your cravings with our Spicy Beef Taco at Taco Haven. Seasoned beef, cooked to perfection, is nestled in a warm corn tortilla. Topped with spicy salsa, shredded lettuce, and a touch of cheese.", 1L, "http://surl.li/npkwz");
			f26.setRestaurant(r5); // Assuming r5 is the instance of the restaurant with ID 5
			foodModelList.add(f26);

			FoodModel f27 = new FoodModel("Beef Taco", 12, "Indulge in the bold flavors of our Grilled Beef Taco at Taqueria Fiesta. Juicy beef, expertly grilled and seasoned, is folded into a soft corn tortilla. Topped with fresh salsa, onions, and cilantro.", 1L, "http://surl.li/npkxf");
			f27.setRestaurant(r6); // Assuming r6 is the instance of the restaurant with ID 6
			foodModelList.add(f27);

			foodRepo.saveAll(foodModelList);




//			insert admin data

			adminRepository.deleteAll();
			adminRepository.resetId();


			List<AdminModel> AdminModelList = new ArrayList<>();
			AdminModelList.add(new AdminModel("Evelyn","Yang","yangt16@studentdouglascollege.ca"));

			adminRepository.saveAll(AdminModelList);


			//insert user data
			userRepo.deleteAll();
			userRepo.resetId();
			UserModel u1 = new UserModel("daligutierrez@gmail.com", "12345", "Daniza", "Gutierrez", "1111 haro", "234567");
			userRepo.save(u1);

			UserModel u2 = new UserModel("test@test.com", "123456", "TEST", "TEST", "TEST", "TEST");
			userRepo.save(u2);

			UserModel u3 = new UserModel("admin@user.com", "123456", "admin", "admin", "admin", "admin");
			userRepo.save(u3);

			//insert reviews
			reviewRepo.deleteAll();
			reviewRepo.resetId();


			List<ReviewModel> reviewsList = new ArrayList<>();

			ReviewModel rv1 = new ReviewModel(4.5F, "This burger was delicious, the best in town");
			rv1.setFood(f2);
			rv1.setUser_id(u1);

			reviewsList.add(rv1);

			ReviewModel rv2 = new ReviewModel(3.5F, "Good but not as great as people say! ");
			rv2.setFood(f2);
			rv2.setUser_id(u1);

			reviewsList.add(rv2);

			reviewRepo.saveAll(reviewsList);



		};
	}

}
