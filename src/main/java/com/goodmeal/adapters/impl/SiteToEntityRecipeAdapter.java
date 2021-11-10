package com.goodmeal.adapters.impl;

import com.goodmeal.adapters.SiteToEntityAdapter;

import com.goodmeal.entities.*;

import com.goodmeal.repositoriesImplementations.*;

import com.srcsite.edamrequest.impl.EdamIngredientRequest;
import com.srcsite.edamrequest.impl.EdamRecipeRequest;
import com.srcsite.siteDataBase.siteIngredientDataBase.SiteIngredientBase;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteIngredient;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipe;
import com.srcsite.siteDataBase.siteRecipeDataBase.SiteRecipeBase;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SiteToEntityRecipeAdapter implements SiteToEntityAdapter<SiteRecipe, Recipe> {

    private final RecipesRepositoryImplementation recipesRepository;
    private final IngredientsRepositoryImplementation ingredientsRepository;
    private final CuisinesRepositoryImplementation cuisinesRepository;
    private final DishesRepositoryImplementation dishesRepository;
    private final MealsRepositoryImplementation mealsRepository;
    private final HealthDietLabelRepositoryImplementation hdLabelsRepository;
    private final HdLabelTypeRepositoryImplementation hdLabelTypesRepository;
    private final IngredientsToRecipesRepositoryImplementation ingredientsToRecipesRepository;

    public SiteToEntityRecipeAdapter(
            RecipesRepositoryImplementation recipesRepo,
            IngredientsRepositoryImplementation ingredientsRepo,
            CuisinesRepositoryImplementation cuisinesRepo,
            DishesRepositoryImplementation dishesRepo,
            MealsRepositoryImplementation mealsRepo,
            HealthDietLabelRepositoryImplementation hdLabelsRepo,
            HdLabelTypeRepositoryImplementation hdLabelTypesRepo,
            IngredientsToRecipesRepositoryImplementation ingredientsToRecipesRepo
    ){
        this.recipesRepository = recipesRepo;
        this.ingredientsRepository = ingredientsRepo;
        this.cuisinesRepository = cuisinesRepo;
        this.dishesRepository = dishesRepo;
        this.mealsRepository = mealsRepo;
        this.hdLabelsRepository = hdLabelsRepo;
        this.hdLabelTypesRepository = hdLabelTypesRepo;
        this.ingredientsToRecipesRepository = ingredientsToRecipesRepo;
    }

    private String getFirstOrSetDefault(String def, List<String> list){
        if(list != null && list.size() != 0){
            return list.get(0);
        }
        return def;
    }

    private SiteIngredientBase loadIngredients(
            String name
    ) {
        try {
            return new EdamIngredientRequest(
                    "184d0a52",
                    "f291617da6961a97b11fc48b33f6845d",
                    name).sendRequest();
        } catch (Exception exception) {
            System.out.println("======IngredientInterruption=====");
            System.out.println(exception.getMessage());
            System.out.println("=================================");
            try
            {
                Thread.sleep(60_000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            return loadIngredients(name);
        }
    }

    private Ingredient createIngredient(SiteIngredient siteIngredient){
        // getting new site ingredients
        SiteIngredientBase siteIngredientBase = loadIngredients(siteIngredient.getName());

        // getting or creating new ingredients
        List<Ingredient> ingredients =
                new SiteToEntityIngredientBaseAdapter(ingredientsRepository)
                        .transform(siteIngredientBase);

        // result
        List<Ingredient> soughtIngredient = ingredients
                .stream()
                .filter(ingredient ->
                        siteIngredient.getOriginalId().equals(ingredient.getOriginalId()))
                .collect(Collectors.toList());

        if(soughtIngredient.size() == 0){
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

    private Cuisine createCuisine(String cuisine){
        // creating cuisine
        Cuisine cuisineEntity = new Cuisine(cuisine);

        cuisinesRepository.save(cuisineEntity);

        // result
        return cuisineEntity;
    }
    private Dish createDish(String dish){
        // create dish
        Dish dishEntity = new Dish(dish);

        dishesRepository.save(dishEntity);

        // result
        return dishEntity;
    }
    private Meal createMeal(String meal){
        // create meal
        Meal mealEntity = new Meal(meal);

        mealsRepository.save(mealEntity);

        // result
        return mealEntity;
    }
    private HealthDietLabel createHealthDietLabel(String hdLabel, String hdLabelType){
        // create health diet Label
        HdLabelType hdLabelTypeEntity = SiteToEntityAdapter.findOrCreate(
                HdLabelType.class,
                hdLabelType,
                hdLabelTypesRepository,
                HdLabelType::getType,
                this::createHDLabelType,
                hdLabelType
        );
        HealthDietLabel healthDietLabel = new HealthDietLabel(hdLabel, hdLabelTypeEntity);
        hdLabelsRepository.save(healthDietLabel);

        // result
        return healthDietLabel;
    }
    private HdLabelType createHDLabelType(String hdLabelType){
        // create hd label type
        HdLabelType hdLabelTypeEntity = new HdLabelType(hdLabelType);
        hdLabelTypesRepository.save(hdLabelTypeEntity);

        // result
        return hdLabelTypeEntity;
    }
    private IngredientsToRecipes createIngredientToRecipe(IngredientsToRecipes ingredientsToRecipes){
        // create ingredient to recipe
        ingredientsToRecipesRepository.save(ingredientsToRecipes);

        // result
        return ingredientsToRecipes;
    }

    private Recipe createRecipe(SiteRecipe siteRecipe) {
        // getting cuisine
        String cuisineName =
                getFirstOrSetDefault(Cuisine.DEFAULT_NAME, siteRecipe.getCuisines());
        Cuisine cuisine = SiteToEntityAdapter.findOrCreate(
                Cuisine.class,
                cuisineName,
                cuisinesRepository,
                Cuisine::getType,
                this::createCuisine,
                cuisineName
        );

        // getting dish
        String dishName =
                getFirstOrSetDefault(Dish.DEFAULT_NAME, siteRecipe.getDishes());
        Dish dish = SiteToEntityAdapter.findOrCreate(
                Dish.class,
                dishName,
                dishesRepository,
                Dish::getType,
                this::createDish,
                dishName
        );

        // getting meal
        String mealName =
                getFirstOrSetDefault(Meal.DEFAULT_NAME, siteRecipe.getMeals());
        Meal meal = SiteToEntityAdapter.findOrCreate(
                Meal.class,
                siteRecipe.getMeals().get(0),
                mealsRepository,
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
                    hdLabelsRepository,
                    HealthDietLabel::getLabel,
                    (str -> createHealthDietLabel(str, "healths")),
                    label
            ));
        }
        if(siteRecipe.getHealths().size() == 0){
            healthDietLabels.add(createHealthDietLabel(HealthDietLabel.DEFAULT_HEALTHS_NAME, "healths"));
        }

        for (String label : siteRecipe.getCautions()){
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    HealthDietLabel.class,
                    label,
                    hdLabelsRepository,
                    HealthDietLabel::getLabel,
                    (str -> createHealthDietLabel(str, "cautions")),
                    label
            ));
        }
        if(siteRecipe.getCautions().size() == 0){
            healthDietLabels.add(createHealthDietLabel(HealthDietLabel.DEFAULT_CAUTIONS_NAME, "cautious"));
        }

        for (String label : siteRecipe.getDiets()){
            healthDietLabels.add(SiteToEntityAdapter.findOrCreate(
                    HealthDietLabel.class,
                    label,
                    hdLabelsRepository,
                    HealthDietLabel::getLabel,
                    (str -> createHealthDietLabel(str, "diets")),
                    label
            ));
        }
        if(siteRecipe.getDiets().size() == 0){
            healthDietLabels.add(createHealthDietLabel(HealthDietLabel.DEFAULT_DIET_NAME, "diets"));
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
        recipesRepository.save(recipe);

        // creating ingredients to recipes
        for(SiteIngredient siteIngredient : siteRecipe.getSiteIngredients()){
            Ingredient ingredient = SiteToEntityAdapter.findOrCreate(
                    Ingredient.class,
                    siteIngredient.getOriginalId(),
                    ingredientsRepository,
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
                    ingredientsToRecipesRepository,
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
        return (Recipe) SiteToEntityAdapter.findOrCreate(
                Recipe.class,
                siteRecipe.getOriginalId(),
                recipesRepository,
                Recipe::getOriginalId,
                this::createRecipe,
                siteRecipe);
    }
}
