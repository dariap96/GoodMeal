package com.goodmeal.services.impl;

import com.goodmeal.entities.HdLabelType;
import com.goodmeal.repositoriesImplementations.HdLabelTypeRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class HdLabelTypesService implements IService<HdLabelType> {

    @Autowired
    private HdLabelTypeRepositoryImplementation hdLabelTypesRepository;

    @Override
    public Iterable<HdLabelType> findAll() {
        return hdLabelTypesRepository.findAll();
    }
}
