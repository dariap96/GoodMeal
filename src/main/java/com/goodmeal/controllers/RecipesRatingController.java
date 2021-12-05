package com.goodmeal.controllers;

import com.goodmeal.entities.RecipesRating;
import com.goodmeal.entities.User;
import com.goodmeal.services.impl.RecipesRatingService;
import com.goodmeal.services.impl.RecipesService;
import com.goodmeal.services.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/recipe_rating")
@CrossOrigin(origins = "*")
public class RecipesRatingController {
    @Autowired
    RecipesService recipesService;

    @Autowired
    UsersService usersService;

    @Autowired
    RecipesRatingService recipesRatingService;

    @GetMapping(value = "/{recipeId}")
    public Double rating(@PathVariable Integer recipeId) {
        return recipesService.findById(recipeId.longValue()).get().getRating();
    }

    @PostMapping("/new")
    public boolean newRating(@RequestBody RecipesRatingDTO ratingDTO) {
        RecipesRating rating = new RecipesRating(
                recipesService.findById(ratingDTO.getRecipeId()).get(),
                usersService.getUserByLogin(ratingDTO.getUserLogin()),
                ratingDTO.getRating(),
                ratingDTO.getReview()
        );

        boolean is_exists = recipesRatingService.getRating(rating.getRecipe().getId(), rating.getUser().getId()) != null;
        if (is_exists) {
            recipesRatingService.update(rating);
        } else {
            recipesRatingService.create(rating);
        }
        return is_exists;
    }
}
