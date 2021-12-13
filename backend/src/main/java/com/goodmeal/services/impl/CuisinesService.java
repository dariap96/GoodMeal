package com.goodmeal.services.impl;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.repositoriesImplementations.CuisinesRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("jpaCuisinesService")
@Transactional
public class CuisinesService implements IService<Cuisine> {

    private final CuisinesRepositoryImplementation cuisinesRepository;

    @Autowired
    public CuisinesService(CuisinesRepositoryImplementation cuisinesRepository) {
        this.cuisinesRepository = cuisinesRepository;
    }

    @Override
    public Iterable<Cuisine> findAll() {
        return cuisinesRepository.findAll();
    }

    @Override
    public void create(Cuisine cuisine) {
        cuisinesRepository.save(cuisine);
    }

    @Override
    public Optional<Cuisine> findById(Long id) {
        return cuisinesRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return (cuisinesRepository.existsById(id));
    }
}
