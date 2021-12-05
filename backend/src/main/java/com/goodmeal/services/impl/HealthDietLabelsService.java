package com.goodmeal.services.impl;
import com.goodmeal.repositoriesImplementations.HealthDietLabelRepositoryImplementation;
import com.goodmeal.services.IService;
import com.goodmeal.entities.HealthDietLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("jpaHealthDietLabelsService")
@Transactional
@Repository
public class HealthDietLabelsService implements IService<HealthDietLabel>{

    @Autowired
    private HealthDietLabelRepositoryImplementation hdLabelRepository;

    @Override
     public Iterable<HealthDietLabel> findAll() {
        return  hdLabelRepository.findAll();
    }

    @Override
    public void create(HealthDietLabel healthDietLabel) {
        hdLabelRepository.save(healthDietLabel);
    }

    @Override
    public Optional<HealthDietLabel> findById(Long id) {
        return hdLabelRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return (hdLabelRepository.existsById(id));
    }
}
