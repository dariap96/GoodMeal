package com.goodmeal;
import com.goodmeal.testDataLoader.TestDataLoader;
import com.srcsite.edamrequest.impl.EdamRecipeRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Configuration
@SpringBootApplication
@Import({TestDataLoader.class})
public class GoodMealApplication {

	public static void main(String[] args) {
//		SpringApplication.run(GoodMealApplication.class, args);
		System.out.println(new EdamRecipeRequest(
				"86eec527",
				"15ab7f74aaa32f92d53df79c9ecdc948",
				"chicken",
				0,
				100)
				.sendRequest().getRecipes().get(0).getIngredientLines().size());
	}

}
