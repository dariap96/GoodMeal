package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.IngredientsToRecipes;
import com.goodmeal.entities.IngredientsToRecipesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientsToRecipesRepositoryImplementation extends JpaRepository<IngredientsToRecipes, IngredientsToRecipesKey> {

    IngredientsToRecipes getByRecipe_IdAndIngredient_Id(Long id, Long id1);
}
