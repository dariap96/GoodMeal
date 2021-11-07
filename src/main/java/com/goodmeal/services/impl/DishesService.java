package com.goodmeal.services.impl;

import com.goodmeal.entities.Dish;
import com.goodmeal.repositoriesImplementations.DishesRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class DishesService implements IService<Dish> {

    @Autowired
    private DishesRepositoryImplementation dishesRepository;

    @Override
    public Iterable<Dish> findAll() {
        return dishesRepository.findAll();
    }
}
