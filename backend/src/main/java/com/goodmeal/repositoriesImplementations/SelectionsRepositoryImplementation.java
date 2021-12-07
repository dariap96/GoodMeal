package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SelectionsRepositoryImplementation extends JpaRepository<Selection, Long> {

    Selection getSelectionById(Long id);
}
