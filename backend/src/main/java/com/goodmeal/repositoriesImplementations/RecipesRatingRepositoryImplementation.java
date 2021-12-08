package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.RecipesRating;
import com.goodmeal.entities.RecipesRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecipesRatingRepositoryImplementation extends JpaRepository<RecipesRating, RecipesRatingKey> {

    List<RecipesRating> getAllByRecipe_Id(Long recipeId);

    List<RecipesRating> getAllByUser_Id(Long userId);

    RecipesRating getRecipesRatingByRecipe_IdAndUser_Id(Long recipeId, Long userId);
}
