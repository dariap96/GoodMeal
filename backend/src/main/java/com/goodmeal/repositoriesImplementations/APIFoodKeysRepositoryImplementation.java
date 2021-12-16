package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.APIFoodKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface APIFoodKeysRepositoryImplementation extends JpaRepository<APIFoodKey, Long> {
    APIFoodKey getAPIFoodKeyById(Long id);
}
