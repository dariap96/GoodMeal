package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Recipe;
import com.goodmeal.repositories.RecipesRepository;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class RecipesRepositoryImplementation extends JpaEntityRepositoryBase<Recipe, Long> implements RecipesRepository {

    public RecipesRepositoryImplementation() { super(Recipe.class); }

}
