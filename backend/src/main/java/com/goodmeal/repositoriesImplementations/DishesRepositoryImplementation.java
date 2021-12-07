package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DishesRepositoryImplementation extends JpaRepository<Dish, Long> {

    Dish getByType(String s);
}
