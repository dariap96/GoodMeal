package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.APIRecipeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface APIRecipeKeysRepositoryImplementation extends JpaRepository<APIRecipeKey, Long> {
    APIRecipeKey getAPIRecipeKeyById(Long id);
}
