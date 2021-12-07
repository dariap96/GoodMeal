package com.goodmeal.services.impl;

import com.goodmeal.entities.Selection;
import com.goodmeal.repositoriesImplementations.SelectionsRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service("jpaSelectionsService")
@Repository
public class SelectionsService implements IService<Selection> {

    @Autowired
    private SelectionsRepositoryImplementation selectionRepository;

    @Override
    public Iterable<Selection> findAll() {
        return selectionRepository.findAll();
    }

    @Override
    public void create(Selection selection) {
        selectionRepository.save(selection);
    }

    @Override
    public Optional<Selection> findById(Long id) {
        return selectionRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return (selectionRepository.existsById(id));
    }

    public Selection getSelectionById(Long id) {return (selectionRepository.getSelectionById(id));}
}
