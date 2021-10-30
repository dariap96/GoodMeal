package com.goodmeal.repositories;

import com.goodmeal.entities.Dish;
import io.crnk.core.repository.ResourceRepository;

public interface DishesRepository extends ResourceRepository<Dish, Long> {}
