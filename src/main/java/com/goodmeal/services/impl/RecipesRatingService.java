package com.goodmeal.services.impl;

import com.goodmeal.entities.RecipesRating;
import com.goodmeal.repositoriesImplementations.RecipesRatingRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RecipesRatingService implements IService<RecipesRating> {

    @Autowired
    private RecipesRatingRepositoryImplementation recipesRatingRepository;

    @Override
    public Iterable<RecipesRating> findAll() {
        return recipesRatingRepository.findAll();
    }

    @Override
    public void create(RecipesRating recipesRating) {
        recipesRatingRepository.save(recipesRating);
    }

    @Override
    public Optional<RecipesRating> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    public List<RecipesRating> getAllByRecipeId(Long recipeId){
        return recipesRatingRepository.getAllByRecipe_Id(recipeId);
    }

    public List<RecipesRating> getAllByUserId(Long userId){
        return recipesRatingRepository.getAllByUser_Id(userId);
    }
}
