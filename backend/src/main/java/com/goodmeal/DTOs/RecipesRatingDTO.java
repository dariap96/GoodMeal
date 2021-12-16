package com.goodmeal.DTOs;

import com.goodmeal.entities.RecipesRating;
import com.goodmeal.services.impl.RecipesService;
import com.goodmeal.services.impl.UsersService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipesRatingDTO {

    private Long recipeId;
    private String userLogin;
    private int rating;
    private String review;

    public static RecipesRatingDTO toRecipesRatingDTO(RecipesRating rating){
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
