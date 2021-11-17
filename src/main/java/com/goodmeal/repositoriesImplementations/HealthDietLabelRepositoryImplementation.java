package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HealthDietLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface HealthDietLabelRepositoryImplementation
        extends JpaRepository<HealthDietLabel,Long> {
}