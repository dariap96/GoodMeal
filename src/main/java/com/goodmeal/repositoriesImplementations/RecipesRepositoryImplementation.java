package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Recipe;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;


@Component
@JsonApiResource(type="recipe")
public class RecipesRepositoryImplementation extends IRepository<Recipe,Long> {

    public RecipesRepositoryImplementation() {
        super(Recipe.class);
    }

}
