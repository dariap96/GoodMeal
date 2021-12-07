package com.goodmeal.controllers;

import com.goodmeal.entities.RecipesRating;
import com.goodmeal.services.impl.RecipesService;
import com.goodmeal.services.impl.UsersService;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonApiResource(type="recipes_rating")
public class RecipesRatingDTO {

    private Long recipeId;
    private String userLogin;
    private int rating;
    private String review;

    public static RecipesRatingDTO toRecipesRatingDTO(RecipesRating rating) {
        return new RecipesRatingDTO(
                rating.getRecipe().getId(),
                rating.getUser().getLogin(),
                rating.getRating(),
                rating.getReview()
        );
    }

    public static RecipesRating toRecipesRating(
            RecipesService recipesService,
            UsersService usersService,
            RecipesRatingDTO ratingDTO
    ) {
        return new RecipesRating(
                recipesService.findById(ratingDTO.getRecipeId()).get(),
                usersService.getUserByLogin(ratingDTO.getUserLogin()),
                ratingDTO.getRating(),
                ratingDTO.getReview()
        );
    }
}
