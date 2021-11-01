package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.entities.IngridientsToRecipes;
import com.goodmeal.entities.IngridientsToRecipesKey;
import com.goodmeal.entities.Role;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepository;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RolesRepositoryImplementation extends JpaEntityRepositoryBase<Role,Long> implements IRepository<Role,Long> {

    private Map<Long, Role> roles = new HashMap<>();

    public RolesRepositoryImplementation() {
        super(Role.class);
    }

    @Override
    public ResourceList<Role> findAll(QuerySpec querySpec) {
        return querySpec.apply(roles.values());
    }
}
