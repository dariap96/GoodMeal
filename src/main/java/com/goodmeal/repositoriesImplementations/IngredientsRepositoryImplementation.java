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

    public IngredientsRepositoryImplementation() {
        super(Ingredient.class);
    }
}
