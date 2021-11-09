package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Meal;
import io.crnk.core.resource.annotations.JsonApiResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
@JsonApiResource(type = "meal")
public interface MealsRepositoryImplementation extends CrudRepository<Meal,Long> {

}
