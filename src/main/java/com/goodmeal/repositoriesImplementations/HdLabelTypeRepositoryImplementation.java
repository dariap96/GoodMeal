package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HdLabelType;
import com.goodmeal.repositories.IRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface HdLabelTypeRepositoryImplementation
    extends IRepository<HdLabelType, Long> {
}
