package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;

import com.goodmeal.entities.*;

import com.goodmeal.repositoriesImplementations.*;

import com.srcsite.edamrequest.impl.EdamIngredientRequest;
import com.srcsite.siteDataBase.siteIngredientDataBase.Food;
import com.srcsite.siteDataBase.siteIngredientDataBase.Hint;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteIngredient;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipe;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SiteToEntityRecipeAdapter implements SiteToEntityAdapter<SiteRecipe, Recipe> {
    private final EntityManager entityManager;

    public SiteToEntityRecipeAdapter(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    private Ingredient createIngredient(SiteIngredient siteIngredient){
        // getting new site ingredients
        SiteIngredientBase siteIngredientBase =
                new EdamIngredientRequest(
                        "184d0a52",
                        "f291617da6961a97b11fc48b33f6845d",
                        siteIngredient.getName()).sendRequest();
        for(Hint hint : siteIngredientBase.getHints()) {
            System.out.println(hint.getFood().getName());
            System.out.println(hint.getFood().getImageURI());
            System.out.println(hint.getFood().getFoodId());
            System.out.println("====================================");
        }

        // getting or creating new ingredients
        List<Ingredient> ingredients =
                new SiteToEntityIngredientBaseAdapter(entityManager)
                        .transform(siteIngredientBase);

        // result
        return ingredients
                .stream()
                .filter(ingredient -> siteIngredient.getOriginalId().equals(ingredient.getOriginalId()))
                .collect(Collectors.toList()).get(0);
    }

    private Cuisine createCuisine(String cuisine){
        // creating cuisine
        Cuisine cuisineEntity = new Cuisine(cuisine);
        new CuisinesRepositoryImplementation().addToDatabase(cuisineEntity);

        entityManager.persist(cuisineEntity);
        entityManager.flush();

        // result
        return cuisineEntity;
    }
    private Dish createDish(String dish){
        // create dish
        Dish dishEntity = new Dish(dish);

        entityManager.persist(dishEntity);
        entityManager.flush();

        // result
        return dishEntity;
    }
    private Meal createMeal(String meal){
        // create meal
        Meal mealEntity = new Meal(meal);

        entityManager.persist(mealEntity);
        entityManager.flush();

        // result
        return mealEntity;
    }
    private HealthDietLabel createHealthDietLabel(String hdLabel, String hdLabelType){
        // create health diet Label
        HdLabelType hdLabelTypeEntity = SiteToEntityAdapter.findOrCreate(
                HdLabelType.class,
                hdLabelType,
                new HdLabelTypeRepositoryImplementation(),
                HdLabelType::getType,
                this::createHDLabelType,
                hdLabelType
        );
        HealthDietLabel healthDietLabel = new HealthDietLabel(hdLabel, hdLabelTypeEntity);
        entityManager.persist(healthDietLabel);
        entityManager.flush();

        // result
        return healthDietLabel;
    }
    private HdLabelType createHDLabelType(String hdLabelType){
        // create hd label type
        HdLabelType hdLabelTypeEntity = new HdLabelType(hdLabelType);
        entityManager.persist(hdLabelTypeEntity);
        entityManager.flush();

        // result
        return hdLabelTypeEntity;
    }
    private IngredientsToRecipes createIngredientToRecipe(IngredientsToRecipes ingredientsToRecipes){
        // create ingredient to recipe
        entityManager.persist(ingredientsToRecipes);
        entityManager.flush();

        // result
        return ingredientsToRecipes;
    }

    private Recipe createRecipe(SiteRecipe siteRecipe) {
        // getting cuisine
        Cuisine cuisine = SiteToEntityAdapter.findOrCreate(
                Cuisine.class,
                siteRecipe.getCuisines().get(0),
                new CuisinesRepositoryImplementation(),
                Cuisine::getType,
                this::createCuisine,
                siteRecipe.getCuisines().get(0)
        );

        // getting dish
        Dish dish = SiteToEntityAdapter.findOrCreate(
                Dish.class,
                siteRecipe.getDishes().get(0),
                new DishesRepositoryImplementation(),
                Dish::getType,
                this::createDish,
                siteRecipe.getDishes().get(0)
        );

        // getting meal
        Meal meal = SiteToEntityAdapter.findOrCreate(
                Meal.class,
                siteRecipe.getMeals().get(0),
                new MealsRepositoryImplementation(),
                Meal::getType,
                this::createMeal,
                siteRecipe.getMeals().get(0)
        );

        // getting health diet labels
        Set<HealthDietLabel> healthDietLabels = new HashSet<>();
        for (String label : siteRecipe.getHealths()){
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    HealthDietLabel.class,
                    label,
                    new HealthDietLabelRepositoryImplementation(),
                    HealthDietLabel::getLabel,
                    (str -> createHealthDietLabel(str, "healths")),
                    label
            ));
        }

        for (String label : siteRecipe.getCautions()){
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    HealthDietLabel.class,
                    label,
                    new HealthDietLabelRepositoryImplementation(),
                    HealthDietLabel::getLabel,
                    (str -> createHealthDietLabel(str, "cautions")),
                    label
            ));
        }

        for (String label : siteRecipe.getDiets()){
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    HealthDietLabel.class,
                    label,
                    new HealthDietLabelRepositoryImplementation(),
                    HealthDietLabel::getLabel,
                    (str -> createHealthDietLabel(str, "diets")),
                    label
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
                siteRecipe.getOriginalId()
        );
        entityManager.persist(recipe);
        entityManager.flush();

        // creating ingredients to recipes
        for(SiteIngredient siteIngredient : siteRecipe.getSiteIngredients()){
            Ingredient ingredient = SiteToEntityAdapter.findOrCreate(
                    Ingredient.class,
                    siteIngredient.getOriginalId(),
                    new IngredientsRepositoryImplementation(),
                    Ingredient::getOriginalId,
                    this::createIngredient,
                    siteIngredient
            );

            IngredientsToRecipes ingredientsToRecipes =
                    new IngredientsToRecipes(
                            ingredient,
                            recipe,
                            siteIngredient.getQuantity(),
                            siteIngredient.getMeasure()
                    );

            SiteToEntityAdapter.findOrCreate(
                    IngredientsToRecipes.class,
                    ingredientsToRecipes.getRecipe().getOriginalId()
                            + ingredientsToRecipes.getIngredient().getOriginalId(),
                    new IngredientsToRecipesRepositoryImplementation(),
                    (ingredientsToRecipesLambda -> ingredientsToRecipesLambda.getRecipe().getOriginalId()
                            + ingredientsToRecipesLambda.getIngredient().getOriginalId()),
                    this::createIngredientToRecipe,
                    ingredientsToRecipes
            );
        }

        // result
        return recipe;
    }

    @Override
    public Recipe transform(SiteRecipe siteRecipe) {
        return SiteToEntityAdapter.findOrCreate(
                Recipe.class,
                siteRecipe.getOriginalId(),
                new RecipesRepositoryImplementation(),
                Recipe::getOriginalId,
                this::createRecipe,
                siteRecipe);
    }
}
