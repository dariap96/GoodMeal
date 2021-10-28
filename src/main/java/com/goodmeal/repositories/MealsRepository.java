package com.goodmeal.repositories;

import com.goodmeal.entities.Meal;
import io.crnk.core.repository.ResourceRepository;

public interface MealsRepository extends ResourceRepository<Meal, Long> {}
