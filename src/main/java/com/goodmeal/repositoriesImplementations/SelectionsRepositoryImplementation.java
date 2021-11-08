package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Selection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface SelectionsRepositoryImplementation extends CrudRepository<Selection, Long> {
}
