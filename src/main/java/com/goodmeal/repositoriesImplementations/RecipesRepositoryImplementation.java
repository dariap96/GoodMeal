package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Recipe;
import io.crnk.data.jpa.JpaEntityRepository;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Component
public interface RecipesRepositoryImplementation extends JpaRepository<Recipe, Long> {

    Recipe getByOriginalId(String s);
}
