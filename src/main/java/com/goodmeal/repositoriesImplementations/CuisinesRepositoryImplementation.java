package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.repositories.IRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface CuisinesRepositoryImplementation extends IRepository<Cuisine, Long> {
}
