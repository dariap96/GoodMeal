package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CuisinesRepositoryImplementation extends JpaEntityRepositoryBase<Cuisine, Long> implements IRepository<Cuisine,Long> {

    private Map<Long,Cuisine> cuisines = new HashMap<>();

    public CuisinesRepositoryImplementation() {
        super(Cuisine.class);
    }

    @Override
    public ResourceList<Cuisine> findAll(QuerySpec querySpec) {
        return querySpec.apply(cuisines.values());
    }


}
