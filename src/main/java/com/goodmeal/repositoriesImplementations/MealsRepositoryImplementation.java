package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Meal;
import com.goodmeal.repositories.IRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MealsRepositoryImplementation
        extends IRepository<Meal,Long> {
}
