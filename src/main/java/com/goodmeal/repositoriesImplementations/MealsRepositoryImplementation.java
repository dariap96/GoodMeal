package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Meal;
import com.goodmeal.repositories.MealsRepository;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class MealsRepositoryImplementation extends JpaEntityRepositoryBase<Meal, Long> implements MealsRepository {

    public MealsRepositoryImplementation() { super(Meal.class); }

}
