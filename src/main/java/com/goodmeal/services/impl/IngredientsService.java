package com.goodmeal.services.impl;

import com.goodmeal.entities.Ingredient;
import com.goodmeal.repositoriesImplementations.IngredientsRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class IngredientsService implements IService<Ingredient> {

    @Autowired
    private IngredientsRepositoryImplementation ingredientsRepository;

    @Override
    public Iterable<Ingredient> findAll() {
        return ingredientsRepository.findAll();
    }
}
