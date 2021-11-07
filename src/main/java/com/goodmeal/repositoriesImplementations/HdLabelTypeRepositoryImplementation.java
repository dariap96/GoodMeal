package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.HdLabelType;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

@JsonApiResource(type = "hd_label_type")
@Component
public class HdLabelTypeRepositoryImplementation
    extends JpaEntityRepositoryBase<HdLabelType, Long>
        implements IRepository<HdLabelType,Long> {

    public HdLabelTypeRepositoryImplementation() {
        super(HdLabelType.class);
    }

}
