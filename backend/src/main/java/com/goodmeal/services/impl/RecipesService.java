package com.goodmeal.services.impl;

import com.goodmeal.entities.Recipe;
import com.goodmeal.repositoriesImplementations.RecipesRepositoryImplementation;
import com.goodmeal.services.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service("jpaRecipesService")
public class RecipesService implements IService<Recipe>{

    private final RecipesRepositoryImplementation recipesRepository;

    public RecipesRepositoryImplementation getRecipesRepository() {
        return recipesRepository;
    }

    @Autowired
    public RecipesService(RecipesRepositoryImplementation recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    @Override
    public Iterable<Recipe> findAll() {
        return recipesRepository.findAll();
    }

    @Override
    public void create(Recipe recipe) {
        recipesRepository.save(recipe);
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return recipesRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return recipesRepository.existsById(id);
    }
}
