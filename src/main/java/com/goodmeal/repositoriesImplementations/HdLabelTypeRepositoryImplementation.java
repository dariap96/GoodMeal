package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HdLabelType;
import com.goodmeal.repositories.IRepository;
import org.springframework.stereotype.Component;

@Component
public class HdLabelTypeRepositoryImplementation
    extends IRepository<HdLabelType,Long> {

    public HdLabelTypeRepositoryImplementation() {
        super(HdLabelType.class);
    }

}
