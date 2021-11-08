package com.goodmeal.services.impl;

import com.goodmeal.entities.Meal;
import com.goodmeal.entities.Recipe;
import com.goodmeal.repositoriesImplementations.RecipesRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
}
