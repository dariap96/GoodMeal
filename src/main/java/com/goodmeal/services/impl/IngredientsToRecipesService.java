package com.goodmeal.services.impl;

import com.goodmeal.entities.IngredientsToRecipes;
import com.goodmeal.repositoriesImplementations.IngredientsToRecipesRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
