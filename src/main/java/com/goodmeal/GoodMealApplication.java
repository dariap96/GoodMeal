package com.goodmeal;
import com.goodmeal.testDataLoader.TestDataLoader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@SpringBootApplication
@CrossOrigin(origins = "*")
@Import({TestDataLoader.class})
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
}
