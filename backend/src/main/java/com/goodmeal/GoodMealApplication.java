package com.goodmeal;

import com.goodmeal.config.WebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Configuration
@SpringBootApplication
public class GoodMealApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodMealApplication.class, args);
	}

}
