package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.IngredientsToRecipes;
import com.goodmeal.entities.IngredientsToRecipesKey;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientsToRecipesRepositoryImplementation extends JpaEntityRepositoryBase<IngredientsToRecipes, IngredientsToRecipesKey> implements IRepository<IngredientsToRecipes, IngredientsToRecipesKey> {

    private Map<IngredientsToRecipesKey, IngredientsToRecipes> ingredientsToRecipes = new HashMap<>();

    public IngredientsToRecipesRepositoryImplementation() {
        super(IngredientsToRecipes.class);
    }

    @Override
    public ResourceList<IngredientsToRecipes> findAll(QuerySpec querySpec) {
        return querySpec.apply(ingredientsToRecipes.values());
    }
}
