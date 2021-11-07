package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Recipe;
import com.goodmeal.repositories.IRepository;
import org.springframework.stereotype.Component;

@Component
public interface RecipesRepositoryImplementation
        extends IRepository<Recipe, Long> {
}
