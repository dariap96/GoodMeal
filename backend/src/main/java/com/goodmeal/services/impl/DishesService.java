package com.goodmeal.services.impl;

import com.goodmeal.entities.Dish;
import com.goodmeal.repositoriesImplementations.DishesRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("jpaDishesService")
@Repository
@Transactional
public class DishesService implements IService<Dish> {

    @Autowired
    private DishesRepositoryImplementation dishesRepository;

    @Override
    public Iterable<Dish> findAll() {
        return dishesRepository.findAll();
    }

    @Override
    public void create(Dish dish) {
        dishesRepository.save(dish);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishesRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return (dishesRepository.existsById(id));
    }
}
