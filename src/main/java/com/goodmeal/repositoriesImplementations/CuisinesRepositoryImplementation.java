package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.repositories.CuisinesRepository;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class CuisinesRepositoryImplementation extends JpaEntityRepositoryBase<Cuisine, Long> implements CuisinesRepository {

    public CuisinesRepositoryImplementation() { super(Cuisine.class); }

}
