package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HealthDietLabel;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@JsonApiResource(type = "healthDietLabel")
@Component
public class HealthDietLabelRepositoryImplementation
        extends JpaEntityRepositoryBase<HealthDietLabel, Long>
        implements IRepository<HealthDietLabel,Long> {

    public HealthDietLabelRepositoryImplementation() {
        super(HealthDietLabel.class);
    }

}