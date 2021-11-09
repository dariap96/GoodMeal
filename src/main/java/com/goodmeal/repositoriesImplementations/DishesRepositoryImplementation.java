package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface DishesRepositoryImplementation extends CrudRepository<Dish, Long> {
}
