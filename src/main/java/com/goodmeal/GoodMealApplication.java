package com.goodmeal;
import com.goodmeal.testDataLoader.TestDataLoader;
import com.srcsite.edamrequest.impl.EdamRecipeRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@SpringBootApplication
@CrossOrigin(origins = "*")
//@Import({TestDataLoader.class})
public class GoodMealApplication {

	@GetMapping("/")
	public String login() {
		return "authenticated successfully";
	}

	/*
	@GetMapping("/home")
	public String getUsers() {
		return "get users page";
	}
	 */

	public static void main(String[] args) {
		SpringApplication.run(GoodMealApplication.class, args);
	}

	/*
	public static void dataLoader() {
		System.out.println(new EdamRecipeRequest(
				"86eec527",
				"15ab7f74aaa32f92d53df79c9ecdc948",
				"chicken",
				0,
				100)
				.sendRequest().getRecipes().get(0).getIngredientLines().size());
	}
	 */
}
