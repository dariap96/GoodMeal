package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.entities.IngridientsToRecipes;
import com.goodmeal.entities.IngridientsToRecipesKey;
import com.goodmeal.entities.User;
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
public class UsersRepositoryImplementation extends ResourceRepositoryBase<User,Long> implements IRepository<User,Long> {

    private Map<Long, User> users = new HashMap<>();

    public UsersRepositoryImplementation() {
        super(User.class);
    }

    @Override
    public ResourceList<User> findAll(QuerySpec querySpec) {
        return querySpec.apply(users.values());
    }
}
