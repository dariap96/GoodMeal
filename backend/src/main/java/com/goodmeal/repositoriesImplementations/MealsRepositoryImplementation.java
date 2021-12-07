package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MealsRepositoryImplementation extends JpaRepository<Meal,Long> {

    Meal getByType(String s);
}
