package com.goodmeal;
import com.goodmeal.entities.User;
import com.goodmeal.security.UserServiceImplementation;
import com.goodmeal.testDataLoader.TestDataLoader;
import com.srcsite.edamrequest.impl.EdamRecipeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@Configuration
@RestController
@SpringBootApplication
@CrossOrigin(origins = "*")
public class GoodMealApplication {

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
}
