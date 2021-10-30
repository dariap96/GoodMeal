package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Selection;
import com.goodmeal.repositories.SelectionsRepository;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class SelectionsRepositoryImplementation extends JpaEntityRepositoryBase<Selection, Long> implements SelectionsRepository {

    public SelectionsRepositoryImplementation() { super(Selection.class); }

}
