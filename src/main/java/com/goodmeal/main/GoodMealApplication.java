package com.goodmeal.main;
import com.goodmeal.entities.*;
import org.springframework.beans.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
