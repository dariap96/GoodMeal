package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Dish;
import com.goodmeal.repositories.IRepository;
import org.springframework.stereotype.Component;

@Component
public class DishesRepositoryImplementation extends IRepository<Dish, Long> {

    public DishesRepositoryImplementation() {
        super(Dish.class);
    }

}
