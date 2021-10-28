package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Dish;
import com.goodmeal.repositories.DishesRepository;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class DishesRepositoryImplementation extends JpaEntityRepositoryBase<Dish, Long> implements DishesRepository {

    public DishesRepositoryImplementation() { super(Dish.class); }

}
