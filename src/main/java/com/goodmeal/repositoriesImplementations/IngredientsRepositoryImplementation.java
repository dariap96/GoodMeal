package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Ingredient;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientsRepositoryImplementation extends JpaEntityRepositoryBase<Ingredient,Long> implements IRepository<Ingredient,Long> {

    private Map<Long, Ingredient> ingredients = new HashMap<>();

    public IngredientsRepositoryImplementation() {
        super(Ingredient.class);
    }

    @Override
    public ResourceList<Ingredient> findAll(QuerySpec querySpec) {
        return querySpec.apply(ingredients.values());
    }
}
