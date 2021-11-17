package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Component
public interface IngredientsRepositoryImplementation extends JpaRepository<Ingredient,Long> {

}
