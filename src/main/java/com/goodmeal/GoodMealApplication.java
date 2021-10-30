package com.goodmeal;
import com.goodmeal.entities.*;
import com.goodmeal.testDataLoader.TestDataLoader;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Configuration
@SpringBootApplication
@Import({TestDataLoader.class})
public class GoodMealApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodMealApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void testJpaMethods(){
		Ingridient ingridient = new Ingridient();
        ingridient.setName("ingridient");
        ingridient.setCarbs(2.0F);
	}
}
