package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Ingredient;
import com.goodmeal.repositories.IRepository;
import org.springframework.stereotype.Component;
import org.springframework.data.repository.CrudRepository;

@Component
public interface IngredientsRepositoryImplementation extends IRepository<Ingredient,Long> {

}
