package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.entities.IngridientsToRecipes;
import com.goodmeal.entities.IngridientsToRecipesKey;
import com.goodmeal.entities.Meal;
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
public class MealsRepositoryImplementation extends JpaEntityRepositoryBase<Meal,Long> implements IRepository<Meal,Long> {

    private Map<Long, Meal> meals = new HashMap<>();

    public MealsRepositoryImplementation() {
        super(Meal.class);
    }

    @Override
    public ResourceList<Meal> findAll(QuerySpec querySpec) {
        return querySpec.apply(meals.values());
    }
}
