package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HealthDietLabel;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HealthDietLabelRepositoryImplementation
        extends JpaEntityRepositoryBase<HealthDietLabel, Long>
        implements IRepository<HealthDietLabel,Long> {

    private Map<Long, HealthDietLabel> healthDietLabels = new HashMap<>();

    public HealthDietLabelRepositoryImplementation() {
        super(HealthDietLabel.class);
    }

    @Override
    public ResourceList<HealthDietLabel> findAll(QuerySpec querySpec) {
        return querySpec.apply(healthDietLabels.values());
    }


}