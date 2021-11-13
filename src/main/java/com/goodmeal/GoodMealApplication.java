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
@SpringBootApplication
@Import({TestDataLoader.class})
public class GoodMealApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodMealApplication.class, args);
	}

}
