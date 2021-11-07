package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.repositories.IRepository;
import org.springframework.stereotype.Component;

@Component
public class CuisinesRepositoryImplementation extends IRepository<Cuisine,Long> {
  // RegistryEntry.addEntry(Cuisine, RegistryEntry registryEntry);
    public CuisinesRepositoryImplementation() {
        super(Cuisine.class);
    }
}
