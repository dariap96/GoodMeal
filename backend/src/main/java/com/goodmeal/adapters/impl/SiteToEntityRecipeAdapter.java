package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;
import com.goodmeal.entities.*;

import com.goodmeal.utils.DataLoader;
import com.goodmeal.services.impl.*;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteIngredient;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipe;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SiteToEntityRecipeAdapter implements SiteToEntityAdapter<SiteRecipe, Recipe> {

    private final RecipesService recipesService;
    private final IngredientsService ingredientsService;
    private final CuisinesService cuisinesService;
    private final DishesService dishesService;
    private final MealsService mealsService;
    private final HealthDietLabelsService healthDietLabelsService;
    private final HdLabelTypesService hdLabelTypesService;
    private final IngredientsToRecipesService ingredientsToRecipesService;
    private final APIFoodKeysService apiFoodKeysService;

    private String getFirstOrSetDefault(String def, List<String> list) {
        if(list != null && list.size() != 0){
            return list.get(0);
        }
        return def;
    }

    private Ingredient createIngredient(SiteIngredient siteIngredient) {
        // getting new site ingredients
        SiteIngredientBase siteIngredientBase = DataLoader.loadIngredients(apiFoodKeysService, siteIngredient.getName());

        // getting or creating new ingredients
        List<Ingredient> ingredients =
                new SiteToEntityIngredientBaseAdapter(ingredientsService)
                        .transform(siteIngredientBase);

        // result
        List<Ingredient> soughtIngredient = ingredients
                .stream()
                .filter(ingredient ->
                        siteIngredient.getOriginalId().equals(ingredient.getOriginalId()))
                .collect(Collectors.toList());

        if(soughtIngredient.size() == 0) {
            soughtIngredient = ingredients
                    .stream()
                    .filter(ingredient ->
                            siteIngredient.getName().equals(ingredient.getName()))
                    .collect(Collectors.toList());
            if(soughtIngredient.size() == 0) {
                soughtIngredient = ingredients;
            }
        }

        return soughtIngredient.get(0);
    }

    private Cuisine createCuisine(String cuisine) {
        // creating cuisine
        Cuisine cuisineEntity = new Cuisine(cuisine);

        cuisinesService.getCuisinesRepository().save(cuisineEntity);

        // result
        return cuisineEntity;
    }

    private Dish createDish(String dish) {
        // create dish
        Dish dishEntity = new Dish(dish);

        dishesService.getDishesRepository().save(dishEntity);

        // result
        return dishEntity;
    }

    private Meal createMeal(String meal) {
        // create meal
        Meal mealEntity = new Meal(meal);

        mealsService.getMealsRepository().save(mealEntity);

        // result
        return mealEntity;
    }

    private HealthDietLabel createHealthDietLabel(String hdLabel, String hdLabelType) {
        // create health diet Label
        HdLabelType hdLabelTypeEntity = SiteToEntityAdapter.findOrCreate(
                hdLabelType,
                hdLabelTypesService.getHdLabelTypesRepository()::getByType,
                this::createHDLabelType,
                hdLabelType
        );
        HealthDietLabel healthDietLabel = new HealthDietLabel(hdLabel, hdLabelTypeEntity);
        healthDietLabelsService.getHdLabelRepository().save(healthDietLabel);

        // result
        return healthDietLabel;
    }

    private HdLabelType createHDLabelType(String hdLabelType) {
        // create hd label type
        HdLabelType hdLabelTypeEntity = new HdLabelType(hdLabelType);
        hdLabelTypesService.getHdLabelTypesRepository().save(hdLabelTypeEntity);

        // result
        return hdLabelTypeEntity;
    }

    private IngredientsToRecipes createIngredientToRecipe(IngredientsToRecipes ingredientsToRecipes) {
        // create ingredient to recipe
        ingredientsToRecipesService.getIngredientsToRecipesRepository().save(ingredientsToRecipes);

        // result
        return ingredientsToRecipes;
    }

    private Recipe createRecipe(SiteRecipe siteRecipe) {
        // getting cuisine
        String cuisineName =
                getFirstOrSetDefault(Cuisine.DEFAULT_NAME, siteRecipe.getCuisines());
        Cuisine cuisine = SiteToEntityAdapter.findOrCreate(
                cuisineName,
                cuisinesService.getCuisinesRepository()::getByType,
                this::createCuisine,
                cuisineName
        );

        // getting dish
        String dishName =
                getFirstOrSetDefault(Dish.DEFAULT_NAME, siteRecipe.getDishes());
        Dish dish = SiteToEntityAdapter.findOrCreate(
                dishName,
                dishesService.getDishesRepository()::getByType,
                this::createDish,
                dishName
        );

        // getting meal
        String mealName =
                getFirstOrSetDefault(Meal.DEFAULT_NAME, siteRecipe.getMeals());
        Meal meal = SiteToEntityAdapter.findOrCreate(
                siteRecipe.getMeals().get(0),
                mealsService.getMealsRepository()::getByType,
                this::createMeal,
                siteRecipe.getMeals().get(0)
        );

        // getting health diet labels
        Set<HealthDietLabel> healthDietLabels = new HashSet<>();
        for (String label : siteRecipe.getHealths()) {
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    label,
                    healthDietLabelsService.getHdLabelRepository()::getByLabel,
                    (str -> createHealthDietLabel(str, HdLabelType.Types.diets.name())),
                    label
            ));
        }

        if(siteRecipe.getHealths().size() == 0) {
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    HealthDietLabel.Defaults.healths.getTitle(),
                    healthDietLabelsService.getHdLabelRepository()::getByLabel,
                    (str -> createHealthDietLabel(str, HdLabelType.Types.healths.name())),
                    HealthDietLabel.Defaults.healths.getTitle()
            ));
        }

        for (String label : siteRecipe.getCautions()) {
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    label,
                    healthDietLabelsService.getHdLabelRepository()::getByLabel,
                    (str -> createHealthDietLabel(str, HdLabelType.Types.cautions.name())),
                    label
            ));
        }
        if(siteRecipe.getCautions().size() == 0) {
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    HealthDietLabel.Defaults.cautions.getTitle(),
                    healthDietLabelsService.getHdLabelRepository()::getByLabel,
                    (str -> createHealthDietLabel(str, HdLabelType.Types.cautions.name())),
                    HealthDietLabel.Defaults.cautions.getTitle()
            ));
        }

        for (String label : siteRecipe.getDiets()) {
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    label,
                    healthDietLabelsService.getHdLabelRepository()::getByLabel,
                    (str -> createHealthDietLabel(str, HdLabelType.Types.diets.name())),
                    label
            ));
        }

        if(siteRecipe.getDiets().size() == 0) {
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    HealthDietLabel.Defaults.healths.getTitle(),
                    healthDietLabelsService.getHdLabelRepository()::getByLabel,
                    (str -> createHealthDietLabel(str, HdLabelType.Types.diets.name())),
                    HealthDietLabel.Defaults.healths.getTitle()
            ));
        }

        // creating recipe
        Recipe recipe = new Recipe(
                siteRecipe.getName(),
                (int)siteRecipe.getCookTime(),
                siteRecipe.getImageURI(),
                String.join(SiteRecipe.IL_SEPARATOR , siteRecipe.getIngredientLines()),
                cuisine,
                meal,
                dish,
                siteRecipe.getOriginalId(),
                healthDietLabels
        );
        recipesService.getRecipesRepository().save(recipe);

        // creating ingredients to recipes
        for(SiteIngredient siteIngredient : siteRecipe.getSiteIngredients()) {
            Ingredient ingredient = SiteToEntityAdapter.findOrCreate(
                    siteIngredient.getName(),
                    ingredientsService.getIngredientsRepository()::getByName,
                    this::createIngredient,
                    siteIngredient
            );

            IngredientsToRecipes ingredientsToRecipes =
                    ingredientsToRecipesService.getIngredientsToRecipesRepository()
                            .getByRecipe_IdAndIngredient_Id(
                                    recipe.getId(),
                                    ingredient.getId()
                            );
            if(ingredientsToRecipes == null) {
                ingredientsToRecipes =
                        new IngredientsToRecipes(
                                ingredient,
                                recipe,
                                siteIngredient.getQuantity(),
                                siteIngredient.getMeasure()
                        );
                ingredientsToRecipesService.getIngredientsToRecipesRepository().save(ingredientsToRecipes);
            }
        }

        // result
        return recipe;
    }

    @Override
    public Recipe transform(SiteRecipe siteRecipe) {
        return (Recipe) SiteToEntityAdapter.findOrCreate(
                siteRecipe.getOriginalId(),
                recipesService.getRecipesRepository()::getByOriginalId,
                this::createRecipe,
                siteRecipe);
    }
}
