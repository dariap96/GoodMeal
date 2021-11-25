package com.goodmeal.controllers;

import com.goodmeal.services.impl.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(value = "/recipe_rating")
@CrossOrigin(origins = "*")
public class RecipesRatingController {
    @Autowired
    RecipesService recipesService;

    @GetMapping(value = "/recipe_rating/{recipeId}")
    public String rating(
            @PathVariable Integer recipeId,
            ModelMap model) {
        model.addAttribute(
                "rating",
                "Rating:" + String.valueOf(recipesService.findById(recipeId.longValue()).get().getRating()));
        return "Rating: " + String.valueOf(recipesService.findById(recipeId.longValue()).get().getRating());
    }
}
