package com.goodmeal.services.impl;

import com.goodmeal.entities.Selection;
import com.goodmeal.repositoriesImplementations.SelectionsRepositoryImplementation;
import com.goodmeal.services.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service("jpaSelectionsService")
public class SelectionsService implements IService<Selection> {

    private final SelectionsRepositoryImplementation selectionRepository;

    @Autowired
    public SelectionsService(SelectionsRepositoryImplementation selectionRepository) {
        this.selectionRepository = selectionRepository;
    }

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

    public void saveSelection(Selection selection) { selectionRepository.save(selection); }

    public void deleteSelectionById(Long id) { selectionRepository.deleteById(id); }
}
