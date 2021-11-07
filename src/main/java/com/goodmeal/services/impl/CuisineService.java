package com.goodmeal.services.impl;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.repositoriesImplementations.CuisinesRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
@Repository
@Transactional
public class CuisineService implements IService<Cuisine> {
    @Autowired
    private CuisinesRepositoryImplementation cuisinesRepository;

    @Override
    public List<Cuisine> findAll() {
        return new LinkedList<Cuisine>((Collection<? extends Cuisine>)cuisinesRepository.findAll());
    }
}
