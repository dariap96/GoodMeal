package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Selection;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SelectionsRepositoryImplementation extends JpaEntityRepositoryBase<Selection,Long> implements IRepository<Selection,Long> {

    public SelectionsRepositoryImplementation() {
        super(Selection.class);
    }

}
