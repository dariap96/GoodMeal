package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.entities.Dish;
import com.goodmeal.entities.Ingridient;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepository;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class IngridientsRepositoryImplementation extends JpaEntityRepositoryBase<Ingridient,Long> implements IRepository<Ingridient,Long> {

    private Map<Long, Ingridient> ingridients = new HashMap<>();

    public IngridientsRepositoryImplementation() {
        super(Ingridient.class);
    }

    @Override
    public ResourceList<Ingridient> findAll(QuerySpec querySpec) {
        return querySpec.apply(ingridients.values());
    }
}
