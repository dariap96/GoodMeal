package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HdLabelType;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;

import java.util.HashMap;
import java.util.Map;

public class HdLabelTypeRepositoryImplementation
    extends JpaEntityRepositoryBase<HdLabelType, Long>
        implements IRepository<HdLabelType,Long> {

    private Map<Long, HdLabelType> hdLabelTypes = new HashMap<>();

    public HdLabelTypeRepositoryImplementation() {
        super(HdLabelType.class);
    }

    @Override
    public ResourceList<HdLabelType> findAll(QuerySpec querySpec) {
        return querySpec.apply(hdLabelTypes.values());
    }
}
