package com.goodmeal.controllers;

import com.goodmeal.entities.RecipesRating;
import com.goodmeal.entities.User;
import com.goodmeal.services.impl.RecipesRatingService;
import com.goodmeal.services.impl.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/recipe_review")
@CrossOrigin(origins = "*")
public class RecipesRatingController {
    @Autowired
    RecipesService recipesService;
    RecipesRatingService ratingService;

    @GetMapping(value = "/{recipeId}")
    public Double rating(@PathVariable Integer recipeId) {
        return recipesService.findById(recipeId.longValue()).get().getRating();
    }

    @PostMapping("/new_recipe_review")
    public boolean newRating(@RequestBody RecipesRating rating) {
        boolean is_exists = ratingService.getRating(rating.getRecipe().getId(), rating.getUser().getId()) != null;
        ratingService.update(rating);
        return is_exists;
    }
}
