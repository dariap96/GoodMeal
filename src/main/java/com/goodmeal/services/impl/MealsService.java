package com.goodmeal.services.impl;

import com.goodmeal.entities.Meal;
import com.goodmeal.repositoriesImplementations.MealsRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("jpaMealsService")
@Repository
public class MealsService implements IService<Meal> {

    @Autowired
    MealsRepositoryImplementation mealsRepository;

    @Override
    public Iterable<Meal> findAll() {
        return mealsRepository.findAll();
    }
}
