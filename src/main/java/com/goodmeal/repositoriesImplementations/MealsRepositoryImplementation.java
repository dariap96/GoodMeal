package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Meal;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@JsonApiResource(type = "meal")
public class MealsRepositoryImplementation extends JpaEntityRepositoryBase<Meal,Long> implements IRepository<Meal,Long> {

    public MealsRepositoryImplementation() {
        super(Meal.class);
    }

}
