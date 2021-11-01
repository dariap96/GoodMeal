package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.entities.IngridientsToRecipes;
import com.goodmeal.entities.IngridientsToRecipesKey;
import com.goodmeal.entities.Selection;
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
public class SelectionsRepositoryImplementation extends JpaEntityRepositoryBase<Selection,Long> implements IRepository<Selection,Long> {

    private Map<Long, Selection> selections = new HashMap<>();

    public SelectionsRepositoryImplementation() {
        super(Selection.class);
    }

    @Override
    public ResourceList<Selection> findAll(QuerySpec querySpec) {
        return querySpec.apply(selections.values());
    }
}
