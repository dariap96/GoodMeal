package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Dish;
import com.goodmeal.repositories.IRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface DishesRepositoryImplementation extends IRepository<Dish, Long> {
}
