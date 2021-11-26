package com.goodmeal.services.impl;

import com.goodmeal.entities.RecipesRating;
import com.goodmeal.entities.RecipesRatingKey;
import com.goodmeal.repositoriesImplementations.RecipesRatingRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service("jpaRecipesRatingService")
@Repository
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

    public RecipesRating getRating(Long recipeId, Long userId) {
        return recipesRatingRepository.getRecipesRatingByRecipe_IdAndUser_Id(recipeId, userId);
    }

    public void update(RecipesRating rating) {
        RecipesRatingKey ratingId = rating.getId();
        RecipesRating exists_rating = getRating(ratingId.getRecipeId(), ratingId.getUserId());
        if(exists_rating != null) {
            recipesRatingRepository.delete(rating);
        }
        recipesRatingRepository.save(rating);
    }
}
