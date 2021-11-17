package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Meal;
import io.crnk.core.resource.annotations.JsonApiResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MealsRepositoryImplementation extends JpaRepository<Meal,Long> {
}
