package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CuisinesRepositoryImplementation extends JpaRepository<Cuisine, Long> {


    Cuisine getByType(String s);
}
