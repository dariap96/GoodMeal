package com.goodmeal.controllers;

import com.goodmeal.entities.RecipesRating;
import com.goodmeal.entities.Role;
import com.goodmeal.entities.User;
import com.goodmeal.services.impl.RecipesRatingService;
import com.goodmeal.services.impl.RecipesService;
import com.goodmeal.services.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/recipe_rating")
@CrossOrigin(origins = "*")
public class RecipesRatingController {
    public static final int MAX_REVIEWS = 20;

    @Autowired
    RecipesService recipesService;

    @Autowired
    UsersService usersService;

    @Autowired
    RecipesRatingService recipesRatingService;

    @GetMapping(value = "/{recipeId}")
    public Double rating(@PathVariable Long recipeId) {
        return recipesService.findById(recipeId).get().getRating();
    }

    @PostMapping("/new")
    public boolean newRating(@RequestBody RecipesRatingDTO ratingDTO) {

        RecipesRating rating = RecipesRatingDTO.toRecipesRating(
                recipesService,
                usersService,
                ratingDTO
        );

        boolean is_exists = recipesRatingService.getRating(rating.getRecipe().getId(), rating.getUser().getId()) != null;
        if (is_exists) {
            recipesRatingService.update(rating);
        } else {
            recipesRatingService.create(rating);
        }
        return is_exists;
    }

    @PatchMapping(value = "/remove-by-admin")
    public boolean removeReviewByAdmin(@RequestBody RecipesRatingDTO ratingDTO) {
        RecipesRating rating = recipesRatingService.removeRating(ratingDTO.getRecipeId(), usersService.getUserByLogin(ratingDTO.getUserLogin()).getId());
        return rating != null;
    }

    @GetMapping(value = "/{recipeId}/reviews")
    public List<RecipesRatingDTO> getRecipeReviews(@PathVariable Long recipeId) {
        List<RecipesRatingDTO> ratingDTOS =
                recipesRatingService
                        .getAllByRecipeId(recipeId)
                        .stream()
                        .map(RecipesRatingDTO::toRecipesRatingDTO)
                        .collect(Collectors.toList());
        Collections.shuffle(ratingDTOS);
        return ratingDTOS.subList(0, Math.min(20, ratingDTOS.size()));
    }

    @GetMapping(value = "/user-reviews/{userLogin}")
    public List<RecipesRatingDTO> getUserReviews(@PathVariable String userLogin) {
        User user = usersService.getUserByLogin(userLogin);
        Long userId = user.getId();
        return recipesRatingService
                        .getAllByUserId(userId)
                        .stream()
                        .map(RecipesRatingDTO::toRecipesRatingDTO)
                        .collect(Collectors.toList());
    }
}
