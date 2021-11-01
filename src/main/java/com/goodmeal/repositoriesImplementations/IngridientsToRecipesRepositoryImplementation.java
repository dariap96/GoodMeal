package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.entities.Ingridient;
import com.goodmeal.entities.IngridientsToRecipes;
import com.goodmeal.entities.IngridientsToRecipesKey;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepository;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngridientsToRecipesRepositoryImplementation extends ResourceRepositoryBase<IngridientsToRecipes,IngridientsToRecipesKey> implements IRepository<IngridientsToRecipes,IngridientsToRecipesKey> {

    private Map<IngridientsToRecipesKey, IngridientsToRecipes> ingridientsToRecipes = new HashMap<>();

    public IngridientsToRecipesRepositoryImplementation() {
        super(IngridientsToRecipes.class);
    }

    @Override
    public ResourceList<IngridientsToRecipes> findAll(QuerySpec querySpec) {
        return querySpec.apply(ingridientsToRecipes.values());
    }
}
