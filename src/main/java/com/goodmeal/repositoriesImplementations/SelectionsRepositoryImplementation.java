package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface SelectionsRepositoryImplementation extends JpaRepository<Selection, Long> {

}
