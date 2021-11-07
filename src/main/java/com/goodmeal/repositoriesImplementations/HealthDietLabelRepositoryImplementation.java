package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HealthDietLabel;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.resource.annotations.JsonApiResource;
import org.springframework.stereotype.Component;

@Component
public class HealthDietLabelRepositoryImplementation
        extends IRepository<HealthDietLabel,Long> {

    public HealthDietLabelRepositoryImplementation() {
        super(HealthDietLabel.class);
    }

}