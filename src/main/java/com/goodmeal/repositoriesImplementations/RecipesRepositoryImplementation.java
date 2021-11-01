package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.entities.IngridientsToRecipes;
import com.goodmeal.entities.IngridientsToRecipesKey;
import com.goodmeal.entities.Recipe;
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
public class RecipesRepositoryImplementation extends ResourceRepositoryBase<Recipe, Long> implements IRepository<Recipe,Long> {

    private Map<Long, Recipe> recipes = new HashMap<>();

    public RecipesRepositoryImplementation() {
        super(Recipe.class);
    }

    @Override
    public ResourceList<Recipe> findAll(QuerySpec querySpec) {
        return querySpec.apply(recipes.values());
    }
}
