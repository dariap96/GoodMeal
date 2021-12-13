package com.goodmeal.services.impl;

import com.goodmeal.entities.HdLabelType;
import com.goodmeal.repositoriesImplementations.HdLabelTypeRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("jpaHdLabelTypesService")
@Transactional
public class HdLabelTypesService implements IService<HdLabelType> {

    private final HdLabelTypeRepositoryImplementation hdLabelTypesRepository;

    @Autowired
    public HdLabelTypesService(HdLabelTypeRepositoryImplementation hdLabelTypesRepository) {
        this.hdLabelTypesRepository = hdLabelTypesRepository;
    }

    @Override
    public Iterable<HdLabelType> findAll() {
        return hdLabelTypesRepository.findAll();
    }

    @Override
    public void create(HdLabelType hdLabelType) {
        hdLabelTypesRepository.save(hdLabelType);
    }

    @Override
    public Optional<HdLabelType> findById(Long id) {
        return hdLabelTypesRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return (hdLabelTypesRepository.existsById(id));
    }
}
