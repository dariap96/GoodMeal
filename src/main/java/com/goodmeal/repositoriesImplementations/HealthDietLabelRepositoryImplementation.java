package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HealthDietLabel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface HealthDietLabelRepositoryImplementation
        extends CrudRepository<HealthDietLabel,Long> {
    public HealthDietLabel getByLabel(String label);
}