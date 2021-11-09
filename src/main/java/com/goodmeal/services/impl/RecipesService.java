package com.goodmeal.services.impl;

import com.goodmeal.entities.Recipe;
import com.goodmeal.repositoriesImplementations.RecipesRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service("jpaRecipesService")
@Repository
public class RecipesService implements IService<Recipe>{

    @Autowired
    RecipesRepositoryImplementation recipesRepository;

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
        return (recipesRepository.existsById(id));

    }
}
