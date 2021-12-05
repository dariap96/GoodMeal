package com.goodmeal.services.impl;

import com.goodmeal.entities.IngredientsToRecipes;
import com.goodmeal.repositoriesImplementations.IngredientsToRecipesRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("jpaIngredientsToRecipesService")
@Repository
@Transactional
public class IngredientsToRecipesService implements IService<IngredientsToRecipes> {

    @Autowired
    private IngredientsToRecipesRepositoryImplementation ingredientsToRecipesRepository;

    @Override
    public Iterable<IngredientsToRecipes> findAll() {
        return ingredientsToRecipesRepository.findAll();
    }

    @Override
    public void create(IngredientsToRecipes ingredientsToRecipes) {
        ingredientsToRecipesRepository.save(ingredientsToRecipes);
    }

    @Override  //?
    public Optional<IngredientsToRecipes> findById(Long id) {
        return Optional.empty();
    }

    @Override  //?
    public boolean existsById(Long id) {
        return false;
    }
}
