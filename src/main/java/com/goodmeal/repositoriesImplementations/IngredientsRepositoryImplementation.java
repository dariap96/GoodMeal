package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Ingredient;
import org.springframework.stereotype.Component;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Component
public interface IngredientsRepositoryImplementation
        extends CrudRepository<Ingredient,Long> {
    public Ingredient getByName(String name);
}
