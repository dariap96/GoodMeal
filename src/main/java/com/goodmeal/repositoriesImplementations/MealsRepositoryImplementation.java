package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Meal;
import io.crnk.core.resource.annotations.JsonApiResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MealsRepositoryImplementation
        extends CrudRepository<Meal,Long> {
    public Meal getByType(String type);
}
