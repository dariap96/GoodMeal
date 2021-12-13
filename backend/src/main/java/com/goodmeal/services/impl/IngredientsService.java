package com.goodmeal.services.impl;

import com.goodmeal.entities.Ingredient;
import com.goodmeal.repositoriesImplementations.IngredientsRepositoryImplementation;
import com.goodmeal.services.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("jpaIngredientsService")
@Transactional
public class IngredientsService implements IService<Ingredient> {

    @Autowired
    private IngredientsRepositoryImplementation ingredientsRepository;

    @Override
    public Iterable<Ingredient> findAll() {
        return ingredientsRepository.findAll();
    }

    @Override
    public void create(Ingredient ingredient) {
           ingredientsRepository.save(ingredient);
    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        return ingredientsRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return (ingredientsRepository.existsById(id));
    }
}
