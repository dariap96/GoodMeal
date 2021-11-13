package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.IngredientsToRecipes;
import com.goodmeal.entities.IngredientsToRecipesKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientsToRecipesRepositoryImplementation
        extends CrudRepository<IngredientsToRecipes, IngredientsToRecipesKey> {
    public IngredientsToRecipes getById(IngredientsToRecipesKey id);
    public IngredientsToRecipes getByRecipe_IdAndIngredient_Id(Long recipeId, Long indredientId);
}
