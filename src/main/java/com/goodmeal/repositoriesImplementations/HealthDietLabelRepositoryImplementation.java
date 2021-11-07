package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HealthDietLabel;
import com.goodmeal.repositories.IRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface HealthDietLabelRepositoryImplementation
        extends IRepository<HealthDietLabel, Long> {
}