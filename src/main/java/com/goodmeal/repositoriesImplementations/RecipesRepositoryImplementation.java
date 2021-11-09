package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Recipe;
import io.crnk.core.resource.annotations.JsonApiResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component
@JsonApiResource(type="recipe")
public interface RecipesRepositoryImplementation extends CrudRepository<Recipe, Long> {

}
