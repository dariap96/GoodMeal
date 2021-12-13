package com.goodmeal.controllers;

import com.goodmeal.entities.RecipesRating;
import com.goodmeal.entities.User;
import com.goodmeal.services.impl.RecipesRatingService;
import com.goodmeal.services.impl.RecipesService;
import com.goodmeal.services.impl.UsersService;
import com.goodmeal.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/recipe_rating")
@CrossOrigin(origins = "*")
public class RecipesRatingController {
    public static final int MAX_REVIEWS = 30;

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

    @Transactional
    @PostMapping("/new")
    public boolean newRating(HttpServletRequest request, @RequestBody RecipesRatingDTO ratingDTO) {

        String userLogin = Utils.getLoginFromPrincipal(request);

        if (!Objects.equals(userLogin, ratingDTO.getUserLogin())){
            return false;
        }

        RecipesRating rating =
                recipesRatingService.getRating(
                        usersService.getUserByLogin(userLogin).getId(),
                        ratingDTO.getRecipeId()
                );

        boolean exists = recipesRatingService.getRating(rating.getRecipe().getId(), rating.getUser().getId()) != null;
        if (exists) {
            recipesRatingService.update(rating);
        } else {
            recipesRatingService.create(rating);
        }
        return exists;
    }

    @Transactional
    @PostMapping(value = "/remove-by-admin")
    public boolean removeReviewByAdmin(@RequestBody RecipesRatingDTO ratingDTO) {
        Long userId = usersService.getUserByLogin(ratingDTO.getUserLogin()).getId();
        Long recipeId = ratingDTO.getRecipeId();

        RecipesRating rating = recipesRatingService.getRating(recipeId, userId);
        recipesRatingService.deleteReviewByEntity(rating);
        return true;
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
        return ratingDTOS.subList(0, Math.min(MAX_REVIEWS, ratingDTOS.size()));
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
