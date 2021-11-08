package com.goodmeal.services.impl;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.repositoriesImplementations.CuisinesRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jpaCuisinesService")
@Repository
@Transactional
public class CuisinesService implements IService<Cuisine> {
    @Autowired
    private CuisinesRepositoryImplementation cuisinesRepository;

    @Override
    public Iterable<Cuisine> findAll() {
        return cuisinesRepository.findAll();
    }




}
