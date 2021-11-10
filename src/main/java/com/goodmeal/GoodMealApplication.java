package com.goodmeal;
import com.goodmeal.entities.User;
import com.goodmeal.security.UserServiceImplementation;
import com.goodmeal.services.impl.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import com.goodmeal.testDataLoader.TestDataLoader;
import com.srcsite.edamrequest.impl.EdamRecipeRequest;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

@Configuration
@RestController
@SpringBootApplication
@CrossOrigin(origins = "*")
@Import({TestDataLoader.class})
public class GoodMealApplication {

	@Autowired
	RecipesService recipesService;

	@Autowired
	UserServiceImplementation userService;

	@GetMapping("/")
	public String login() {
		return "authenticated successfully";
	}

	@PostMapping("/register")
	public boolean register(@RequestBody User user) {
		System.out.println(user.getLogin());

		if(!userService.saveUser(user)) { return false; }

		return true;
	}


	public static void main(String[] args) {
		SpringApplication.run(GoodMealApplication.class, args);
	}

	/*
	@EventListener(ApplicationReadyEvent.class)
	public void testJpaMethods(){
		 recipesService.findAll().forEach(it->System.out.println(it.getName()));
	}
	 */

}
