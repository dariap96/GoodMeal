package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RecipesRepositoryImplementation extends JpaRepository<Recipe, Long> {

    Recipe getByOriginalId(String s);

    Recipe getRecipeById(Long id);
}
