package com.goodmeal.services.impl;

import com.goodmeal.entities.Meal;
import com.goodmeal.repositoriesImplementations.MealsRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service("jpaMealsService")
public class MealsService implements IService<Meal> {

    private final MealsRepositoryImplementation mealsRepository;

    @Autowired
    public MealsService(MealsRepositoryImplementation mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    @Override
    public Iterable<Meal> findAll() {
        return mealsRepository.findAll();
    }

    @Override
    public void create(Meal meal) {
        mealsRepository.save(meal);
    }

    @Override
    public Optional<Meal> findById(Long id) {
        return mealsRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return (mealsRepository.existsById(id));
    }
}
