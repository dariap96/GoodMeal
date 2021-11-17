package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HdLabelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface HdLabelTypeRepositoryImplementation
    extends JpaRepository<HdLabelType,Long> {
}
