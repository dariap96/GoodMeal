package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Ingredient;
import com.goodmeal.repositories.IRepository;
import org.springframework.stereotype.Component;

@Component
public class IngredientsRepositoryImplementation extends IRepository<Ingredient,Long> {

    public IngredientsRepositoryImplementation() {
        super(Ingredient.class);
    }
}
