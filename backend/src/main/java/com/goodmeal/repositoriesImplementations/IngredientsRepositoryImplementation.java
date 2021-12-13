package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientsRepositoryImplementation extends JpaRepository<Ingredient,Long> {

    Ingredient getByName(String s);

}
