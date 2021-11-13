package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Ingredient;
import org.springframework.stereotype.Component;
import org.springframework.data.repository.CrudRepository;

@Component
public interface IngredientsRepositoryImplementation
        extends CrudRepository<Ingredient,Long> {
    public Ingredient getByOriginalId(String originalId);
}
