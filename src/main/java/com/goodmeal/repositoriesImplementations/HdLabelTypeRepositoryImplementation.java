package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HdLabelType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface HdLabelTypeRepositoryImplementation
    extends CrudRepository<HdLabelType,Long> {
    public HdLabelType getByType(String type);
}
