package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.User;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class UsersRepositoryImplementation extends IRepository<User,Long> {

    public UsersRepositoryImplementation() {
        super(User.class);
    }

}
