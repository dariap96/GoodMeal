package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RecipesRepositoryImplementation extends JpaRepository<Recipe, Long> {}
