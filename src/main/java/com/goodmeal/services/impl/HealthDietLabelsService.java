package com.goodmeal.services.impl;
import com.goodmeal.repositoriesImplementations.HealthDietLabelRepositoryImplementation;
import com.goodmeal.services.IService;
import com.goodmeal.entities.HealthDietLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Repository
public class HealthDietLabelsService implements IService<HealthDietLabel>{

    @Autowired
    private HealthDietLabelRepositoryImplementation hdLabelRepository;

    @Override
     public Iterable<HealthDietLabel> findAll() {
        return  hdLabelRepository.findAll();
    }

}
