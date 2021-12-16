package com.goodmeal.controllers;

import com.goodmeal.DTOs.RecipesRatingDTO;
import com.goodmeal.DTOs.UpdateRecipesDTO;
import com.goodmeal.adapters.impl.SiteToEntityRecipeBaseAdapter;
import com.goodmeal.entities.RecipesRating;
import com.goodmeal.entities.User;
import com.goodmeal.services.impl.*;
import com.goodmeal.utils.DataLoader;
import com.goodmeal.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private static final String ROLE = "ADMIN";

    @Autowired
    UsersService usersService;

    @Autowired
    RolesService rolesService;

    @Autowired
    RecipesRatingService recipesRatingService;

    @Autowired
    private RecipesService recipesService;
    @Autowired
    private IngredientsService ingredientsService;
    @Autowired
    private CuisinesService cuisinesService;
    @Autowired
    private DishesService dishesService;
    @Autowired
    private MealsService mealsService;
    @Autowired
    private HealthDietLabelsService healthDietLabelsService;
    @Autowired
    private HdLabelTypesService hdLabelTypesService;
    @Autowired
    private IngredientsToRecipesService ingredientsToRecipesService;
    @Autowired
    private APIRecipeKeysService apiRecipeKeysService;
    @Autowired
    private APIFoodKeysService apiFoodKeysService;


    private boolean isAdmin(HttpServletRequest request){
        String userLogin = Utils.getLoginFromPrincipal(request);

        return usersService
                .getUserByLogin(userLogin)
                .getRoleSet()
                .contains(rolesService.getRoleByRole(ROLE));
    }

    @PostMapping(value = "/remove-review")
    public boolean removeReviewByAdmin(
            HttpServletRequest request,
            @RequestBody RecipesRatingDTO ratingDTO
    ) {
        if (!isAdmin(request)) {
            return false;
        }

        Long userId = usersService.getUserByLogin(ratingDTO.getUserLogin()).getId();
        Long recipeId = ratingDTO.getRecipeId();

        RecipesRating rating = recipesRatingService.getRating(recipeId, userId);
        recipesRatingService.deleteReviewByEntity(rating);
        return true;
    }

    @GetMapping(value = "/user-reviews/{userLogin}")
    public List<RecipesRatingDTO> getUserReviews(
            HttpServletRequest request,
            @PathVariable String userLogin
    ) {
        if (!isAdmin(request)) {
            return null;
        }

        User user = usersService.getUserByLogin(userLogin);
        Long userId = user.getId();
        return recipesRatingService
                .getAllByUserId(userId)
                .stream()
                .map(RecipesRatingDTO::toRecipesRatingDTO)
                .collect(Collectors.toList());
    }


    @PostMapping(value = "/recipe-update")
    public int updateByAdmin(
            HttpServletRequest request,
            @RequestBody UpdateRecipesDTO updateRecipesDTO
            ) {
        if (!isAdmin(request)) {
            return -1;
        }

        SiteToEntityRecipeBaseAdapter adapter =
                new SiteToEntityRecipeBaseAdapter(
                        recipesService,
                        ingredientsService,
                        cuisinesService,
                        dishesService,
                        mealsService,
                        healthDietLabelsService,
                        hdLabelTypesService,
                        ingredientsToRecipesService,
                        apiFoodKeysService
                );

        return DataLoader.loadRecipes(
                apiRecipeKeysService,
                adapter,
                updateRecipesDTO.getQuery(),
                updateRecipesDTO.getMeal(),
                updateRecipesDTO.getDish(),
                updateRecipesDTO.getCuisine()
        );
    }

}
