package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Selection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface SelectionsRepositoryImplementation extends CrudRepository<Selection, Long> {
    Set<Selection> findSelectionsByUserId(Long id);
}
